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

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.service.ISpecializationService;

@Controller
@RequestMapping("/employee")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	
	/***
	 * if the user enters "/register" in addressbar 
	 * then this method will be called and 
	 * it will load the SpecializationRegister.html page data from /template folder
	 */
	
	@GetMapping("/register")
	public String showSpecializaionRegister()
	{
		return "SpecializationRegister";
	}
	
	/***
	 * on form submit(/save+POST), the form data will be collected using @ModelAttribute
	 * call service layer with object, and REad id back
	 * create String message using model memory 
	 * send back to UI
	 * Return back to SpecializationRegister.html
	 */
	
	@PostMapping("/save")
	public String saveSpecialization(@ModelAttribute Specialization specialization, Model model)
	{
		Integer id = service.saveSpecialization(specialization);
		String message = "Specialization '"+id +"' Created";
		model.addAttribute("message",message);
		return "SpecializationRegister";
	}
	/***
	 * Fetch date from DB using services
	 * send data to UI using model
	 * retun to SpecializationData.html
	 */
	@GetMapping("/all")
	public String  viewAllSpecialization(Model model)
	{
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("list",list);
			
		return "SpecializationData";
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
	public String deleteSpecialization(@RequestParam Integer id, Model model)
	{
		// Call service
		service.deleteSpecialization(id);
		
		// one success message 
		String message = "Specialization '"+id+"' Deleted successfully";
		model.addAttribute("message",message);
		
		// Get latest data 
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("list", list);
		
		return "SpecializationData";
		
		
	}
	
	/***
	 * show edit page 
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Integer id, Model model)
	{
		//get one specialization...load objct from DB
		Specialization specialization = service.getOneSpecialization(id);
		// sent it to UI
		model.addAttribute("specialization", specialization);
		
		// send to UI
		return "SpecializationEdit";
	}
	
	/***
	 * Do update....Read data from UI page as  ModelAttribute
	 * call services to update the specialization
	 * redirect  back to all
	 */
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization specialization)
	{
		//as per servlet : req.getRequestDispatcher("/all")
		service.UpdateSpecialization(specialization);
		return "redirect:all";
	}
}
