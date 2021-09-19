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

import in.nit.rohit.entity.Doctor;
import in.nit.rohit.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;
	
	/***
	 * when user enter "/register" them this method will be called 
	 *  it will load  form DoctorRegister.html from "/template" folder
	 */
	@GetMapping("/register")
	public String showDoctorRegister()
	{
		return "DoctorRegister";
	}
	
	/***
	 * on form submit(/save + POST), this will collect form data using @ModelAttribute 
	 * Call service layer with object. and read the Id back
	 * create a String message using model memory
	 * sent it ti the UI
	 * Return back to DoctorRegister.html page
	 */
	public String saveDoctor(@ModelAttribute Doctor doctor, Model model)
	{
		Integer id = service.saveDoctor(doctor);
		String message = "Doctor '"+id+"' Created successfully";
		model.addAttribute("message", model);
	    return "DoctorRegister";	
	}
	
	/***
	 * fetch Data from DB using services
	 * send data to UI using Model
	 * Return to DoctorData.html
	 */
	@GetMapping("/all")
	public String getAllEmployee(Model model)
	{
		List<Doctor> list = service.getAllDoctors();
		model.addAttribute("list",list);
		return "DoctorData";
	}
	
	/***
	 * Read from request URL
	 * Call service for Delete 
	 * get latest data 
	 * create success message 
	 * send it to UI using model memory 
	 * Return back to DoctorData.html
	 * 
	 */
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam Integer id, Model model)
	{
		// call service to delete the employee
		service.deleteDoctor(id);
		// create a message 
		String message = "Doctor '"+id+"' Deleted successfully";
		model.addAttribute("message",message);
		
		// get latest data
		List<Doctor> doctor = service.getAllDoctors();
		model.addAttribute("doctor",doctor);
		
		// return to UI
		return "DoctorData";
	}
	
	/***
	 * show edit page
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Integer id, Model model)
	{
		// Get one doctor....Load it from DB
		Doctor doctor = service.getOneDoctor(id);
		// send it to UI
		model.addAttribute("doctor",doctor);
		// show Doctor Edit page
		return "DoctorEdit";
	}
	
	
	/***
	 * To do update Read data from UI page using ModelAttribute 
	 * call service to update the doctor 
	 * redirect to DoctorData page
	 */
	
	@PostMapping("/update")	
	public String updateDoctor(@ModelAttribute Doctor doctorn)
	{
		// as per the servlet: req.getRequestDispatcher("/all")
		return "redirect:all";
	}
}
