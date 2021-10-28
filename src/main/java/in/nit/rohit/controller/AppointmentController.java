package in.nit.rohit.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.rohit.entity.Appointment;
import in.nit.rohit.entity.Doctor;
import in.nit.rohit.exception.AppointmentNotFoundException;
import in.nit.rohit.service.IAppointmentService;
import in.nit.rohit.service.IDoctorService;
import in.nit.rohit.service.ISpecializationService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService service;
	
	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ISpecializationService specializationService;
	
	
	private void doctorListUI(Model model)
	{
		model.addAttribute("doctors", doctorService.getDoctorIdAndName());
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model)
	{
		model.addAttribute("appointment", new Appointment());
		doctorListUI(model);
		return "AppointmentRegister";
		
	}
	
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment , Model model)
	{
		Long id = service.saveAppointment(appointment);
		String message =  "Appointment:-"+ id +"created successfully";
		model.addAttribute("message",message);
		model.addAttribute("appointment", new Appointment());
		doctorListUI(model);
		return "AppointmentRegister";
	}
	
	@GetMapping("/all")
	public String getAllAppointment(Model model, @RequestParam( value = "message" , required = false)String message) {
		List<Appointment> list = service.getAllAppointment();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}
	
	public String deleteAppointment(@RequestParam("id") Long id, RedirectAttributes attributes) {
		
		String message = null;
		try {
			service.deleteAppointment(id);
			message = "Appointment"+id+"deleted Successfully";
			
		}catch(AppointmentNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message", message);
		
		return "redirect:all";
		
	}
	
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id,Model model, RedirectAttributes attributes) {
        String page = null;
        try {
        	model.addAttribute("appointment",service.getOneAppointment(id));
        	doctorListUI(model);
        	page = "AppointmentEdit";
        }catch(AppointmentNotFoundException e)
        {
        	e.printStackTrace();
        	attributes.addAttribute("message",e.getMessage());
        	page = "redirect:all";
        }
		return page;
	}
	
	@PostMapping("/update")
	public String updateAppointment(@ModelAttribute Appointment appointment, RedirectAttributes attributes)
	{
		service.updateAppointment(appointment);
		attributes.addAttribute("message", "Appontment updated Sucessfully");
		return "redirect:all";
	}
	
	// view appointment page 
	@GetMapping("/view")
	public String viewSlots(@RequestParam(required = false, defaultValue="0")Long specId,  Model model) {
		// fetch data for spec dropdown
		Map<Long,String> specMap = specializationService.getSpecIdAndName();
		model.addAttribute("specializations",specMap);
		List<Doctor> docList = null;
		String message = null;
		if(specId <= 0) { // if they did not select any spec
			docList = doctorService.getAllDoctors();
			 message = "Result:All Doctors";
		}else {
			
		    docList = doctorService.findDoctorBySpecId(specId);
		    message = "Result:"+ specializationService.getOneSpecialization(specId).getSpecName();
		}
		
		model.addAttribute("docList",docList);
		model.addAttribute("message", message );
		return "AppointmentSearch";
	}
	
	// View Slot..
	@GetMapping("/viewSlot")
	public String showSlots(@RequestParam Long id, Model model) {
		//fetch appointments based on doctor id
		List<Object[]> list = service.getAppointmentSlotByDoctorId(id);
		model.addAttribute("list", list);
		Doctor doc = doctorService.getOneDoctor(id);
		model.addAttribute("message","Result showing for:"+doc.getFirstName()+" "+doc.getLastName());
		return "AppointmentSlots";
	}

}
