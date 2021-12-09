package in.nit.rohit.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.nit.rohit.entity.Appointment;
import in.nit.rohit.entity.Patient;
import in.nit.rohit.entity.SlotRequest;
import in.nit.rohit.entity.User;
import in.nit.rohit.service.IAppointmentService;
import in.nit.rohit.service.IDoctorService;
import in.nit.rohit.service.IPatientService;
import in.nit.rohit.service.ISlotRequestService;
import in.nit.rohit.service.ISpecializationService;
import in.nit.rohit.service.constant.SlotStatus;
import in.nit.rohit.util.AdminDashboardUtil;
import in.nit.rohit.view.InvoiceSlipPdfView;

@Controller
@RequestMapping("/slots")
public class SlotRequestController {
	
	@Autowired
	private ISlotRequestService slotService;
	
	@Autowired
	private IAppointmentService appointmentService;
	
	@Autowired
	private IPatientService patientService;
	
	@Autowired
	private IDoctorService doctorService;
	
	@Autowired
	private ISpecializationService specializationService; 
	
	@Autowired
	private AdminDashboardUtil util;
	
	@Autowired
	private ServletContext context;
	
	
	// Patient id, Appointment id
	@GetMapping("/book")
	public String bookSlot(@RequestParam Long appid, HttpSession session, Model model)
	{
		
		Appointment app = appointmentService.getOneAppointment(appid);
		
		// for Patient object
		User user = (User) session.getAttribute("userOb");
		String email = user.getUsername();
		Patient patient = patientService.getOneByEmail(email);
		
		// Create slot object
		SlotRequest sr = new SlotRequest();
		sr.setAppointment(app);
		sr.setPatient(patient);
		sr.setStatus(SlotStatus.PENDING.name());
		
		try 
		{
			slotService.saveSlotRequest(sr);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			String appDate = sdf.format(app.getDate());
			String message = "Patient"+(patient.getFirstName()+" "+patient.getLastName())+"Request for appointment to DR."
			+(app.getDoctor().getFirstName()+" "+app.getDoctor().getLastName())+",On Date:"+ appDate +",submitted with status:"+sr.getStatus();
			
			model.addAttribute("message", message);
			
		 	
		}catch(Exception e)
		{
			e.printStackTrace();
			model.addAttribute("message","Bookoing Request is Already made for this Appointment/Date");
			
		}
		
		
		return "SlotRequestMessage";
		
	}
	
	@GetMapping("/all")
	public String viewAllRequestedAppointment(Model model)
	{
		List<SlotRequest> list = slotService.getAllSlotRequests();
		model.addAttribute("list", list);
		return "SlotRequestData";
	}
	
	@GetMapping("/patient")
	public String viewMyRequestedAppointmrnt(Principal principal, Model model)
	{
		String email = principal.getName();
		List<SlotRequest> list = slotService.viewSlotsByPatientMail(email);
		model.addAttribute("list",list);
		return "SlotRequestDataPatient";
		
	}
	
	@GetMapping("/doctor")
	public String viewMyRequestedAppointment(Principal principal, Model model)
	{
		String email = principal.getName();
		List<SlotRequest> list = slotService.viewSlotsByDoctorMail(email);
		model.addAttribute("list", list);
		return "SlotRequestDataDoctor";
	}
	
	@GetMapping("/accept")
	public String updateSlotRequestAsAccept(@RequestParam Long id)
	{
		slotService.updateSlotRequestStatus(id, SlotStatus.ACCEPTED.name());
		SlotRequest sr = slotService.getOneSlotRequest(id);
		if(sr.getStatus().equals(SlotStatus.ACCEPTED.name()))
		{
			appointmentService.updateSlotCountForAppointment(sr.getAppointment().getId(),-1);
		}
		return "redirect:all";
	}
	
	@GetMapping("/reject")
	public String updateSlotRequestAsReject(@RequestParam Long id)
	{
		slotService.updateSlotRequestStatus(id,SlotStatus.REJECTED.name());
		return "redirect:all";
	}
	
	@GetMapping("/cancel")
	public String updateSlotRequestAsCancel(@RequestParam long id)
	{
		
		SlotRequest sr = slotService.getOneSlotRequest(id);
		if(sr.getStatus().equals(SlotStatus.ACCEPTED.name()))
		{
			slotService.updateSlotRequestStatus(id,SlotStatus.CANCELLED.name());
			appointmentService.updateSlotCountForAppointment(sr.getAppointment().getId(), 1);
		}
		return "redirect:patient";
	}
	
	@GetMapping("/dashboard")
	public String adminDashboard(Model model)
	{

		model.addAttribute("doctors",doctorService.getDoctorCount());
		model.addAttribute("patients",patientService.getPatientCount());
		model.addAttribute("appointments",appointmentService.getAppointmentCount());
		model.addAttribute("specialization",specializationService.getSpecializationCount());

		String path = context.getRealPath("/"); //root folder
		
		List<Object[]> list = slotService.getSlotsStatusAndCount();
		util.generateBar(path, list);
		util.generatePie(path, list);
		return "AdminDashboard";
		
		
	}
	
	@GetMapping("/invoice")
	public ModelAndView generateInvoice(@RequestParam Long id) 
	{
		ModelAndView m = new ModelAndView();
		m.setView(new InvoiceSlipPdfView());
		SlotRequest slotRequest=slotService.getOneSlotRequest(id);
		m.addObject("slotRequest", slotRequest);
		return m;
	}
	
	

}
