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

import in.nit.rohit.entity.Patient;
import in.nit.rohit.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {

	@Autowired	
	private IPatientService service;
	
	/***
	 * When user enter the "/register" in address bar 
	 * then this method will be called 
	 * And it show PatientRegister.html page from "/template" folder
	 */
	
	@GetMapping("/register")
	public String showPatientRegister()
	{
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
		Integer id  = service.savePatient(patient);
		String message = "Patient '"+id+"' created successfuly";
		model.addAttribute("message", message);
		return "PatientRegister";
	}
	
	/***
	 * Fetch date from DB using services
	 * send data to UI using model
	 * retun to SpecializationData.html
	 */
	@GetMapping("/all")
	public String viewAllPatient(Model model)
	{
		List<Patient> list = service.getAllPatient();
		model.addAttribute("list",list);
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
	public String deletePatient(@RequestParam Integer id, Model model)
	{
		// get one patient
	        service.deletePatient(id);
		// one success message
	        String message = "Patient '"+id+"' Deleted successfully";
	     // send message to UI
	        model.addAttribute("message", message);
	     // get Latest data 
	        List<Patient> list = service.getAllPatient();
	        model.addAttribute("list",list);
	        
	        return "PatientData";
	}
	
	/***
	 * show edit page 
	 * @return
	 */

	@GetMapping("/edit")
	public String showEditPage(@RequestParam Integer id, Model model)
	{
		// get one empoyee based on id from DB
	    Patient patient = 	service.getOnePatient(id);
	    // sent to UI
	    model.addAttribute("patient",patient);
	    
	    // return to the PatientData.html page
	    return "PatientData";
		
	}
	
	/***
	 * Do update....Read data from UI page as  ModelAttribute
	 * call services to update the specialization
	 * redirect  back to all
	 */
	
	public String updatePatient(@ModelAttribute Patient patient)
	{
		service.savePatient(patient);
		return "redirect:all";
	}
}
