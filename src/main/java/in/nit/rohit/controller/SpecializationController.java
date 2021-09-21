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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {

	@Autowired
	private ISpecializationService service;
	
	
	/***
	 * 	1. show Register page 
	 */
	
	@GetMapping("/register")
	public String showSpecializaionRegister()
	{
		return "SpecializationRegister";
	}
	
	/***
	 *2. On from submit save form data
	 */
	
	@PostMapping("/save")
	public String saveSpecialization(@ModelAttribute Specialization specialization, Model model)
	{
		Long id = service.saveSpecialization(specialization);
		String message = "Specialization '"+id +"' Created";
		model.addAttribute("message",message);
		return "SpecializationRegister";
	}
	/***
	 * Display all Speialization
	 */
	@GetMapping("/all")
	public String  viewAllSpecialization(Model model, @RequestParam(value="message", required=false) String message)
	{
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
			
		return "SpecializationData";
	}
	
	/***
	 * Delete by Id
	 */
	@GetMapping("/delete")
	public String deleteSpecialization(@RequestParam Long id, RedirectAttributes attributes)
	{
		// Call service
		service.deleteSpecialization(id);
		
		// one success message 
		attributes.addAttribute("message","Specialization ("+id+") Deleted successfully");
		
		
		return "redirect:all";
		
		
	}
	
	/***
	 * show edit page 
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model)
	{
		//get one specialization...load objct from DB
		Specialization spec = service.getOneSpecialization(id);
		// sent it to UI
		model.addAttribute("specialization", spec);
		
		// send to UI
		return "SpecializationEdit";
	}
	
	/***
	 * Do update....Read data from UI page as  ModelAttribute
	 * call services to update the specialization
	 * redirect  back to all
	 */
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization specialization, RedirectAttributes attributes)
	{
		//as per servlet : req.getRequestDispatcher("/all")
		service.UpdateSpecialization(specialization);
		attributes.addFlashAttribute("message","Record ("+specialization.getId()+")is updated");
		return "redirect:all";
	}
}
