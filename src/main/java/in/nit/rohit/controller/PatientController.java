package in.nit.rohit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.rohit.entity.Patient;
import in.nit.rohit.exception.PatientNotFoundException;
import in.nit.rohit.service.IPatientService;
import in.nit.rohit.service.IUserService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired	
	private IPatientService service;
	
	@Autowired
	private IUserService userService;
	
	/***
	 * When user enter the "/register" in address bar 
	 * then this method will be called 
	 * And it show PatientRegister.html page from "/template" folder
	 */
	
	@GetMapping("/register")
	public String showPatientRegister(Model model)
	{
		model.addAttribute("patient", new Patient());
		return "PatientRegister";
	}
	
	/***
	 * on click of (/save+POST), the form data will be collected using @ModelAttribute 
	 * Call service layer with object, and read back the id
	 * create String message and 
	 * send to UI
	 * Return to PatientRegister
	 */
	@PostMapping("/save")
	public String savePatient(@ModelAttribute Patient patient, Model model)
	{
		Long id  = service.savePatient(patient);
		String message = "Patient created successfully:"+id;
		model.addAttribute("message", message);
		model.addAttribute("patient", new Patient());
		return "PatientRegister";
	}
	
	/***
	 * Fetch date from DB using services
	 * send data to UI using model
	 * retun to SpecializationData.html
	 */
	@GetMapping("/all")
	public String viewAllPatient(Model model,@RequestParam(value = "message", required = false) String message)
	{
		List<Patient> list = service.getAllPatient();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "PatientData";
	}
	
	/***
	 * Read from request URL
	 * call service for delete
	 * get latest data
	 * create success message 
	 * send it ot UI using Model 
	 * Return back to SpcializationData.html
	 */
	@GetMapping("/delete")
	public String deletePatient(@RequestParam Long id, RedirectAttributes attributes)
	{
		try {
			service.deletePatient(id);
			attributes.addFlashAttribute("message","Patient Deleted with id:"+id);
			
		}catch(PatientNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}
		return "redirect:all";
		
	}
	
	/***
	 * show edit page 
	 * @return
	 */

	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes)
	{
		String page = null;
		try {
			Patient ob = service.getOnePatient(id);
			model.addAttribute("patient",ob);
			page = "PatientEdit";
			
		}catch(PatientNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";
		}
		
		
		return page;
		
	}
	
	/***
	 * Do update....Read data from UI page as  ModelAttribute
	 * call services to update the specialization
	 * redirect  back to all
	 */
	
	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient, RedirectAttributes attributes)
	{
		service.savePatient(patient);
		attributes.addAttribute("message","Patient Updated Successfully");
		return "redirect:all";
	}
	
	
	@GetMapping("/checkEmail")
	@ResponseBody
	public   String validateSpecCode(@RequestParam String email,@RequestParam Long id)
	{
		 String message="";
		 if(id == 0 && userService.isUserEmailExist(email)) {
			 message = email +", already exist";
		 }else if(id !=0 && userService.isUserEmailExistForEdit(email, id)) {
			 message = email +",Already exist ";
		 }
		 return message; // this is not a view name(it is message)
	}
}
