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

import in.nit.rohit.entity.Doctor;
import in.nit.rohit.exception.DoctorNotFoundException;
import in.nit.rohit.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private IDoctorService service;
	
	/***
	 * 1. show Register page
	 * @return
	 */
	@GetMapping("/register")
	public String showDoctorRegister()
	{
		return "DoctorRegister";
	}
	
	/***
	 * 2. save on submit 
	 * @param doctor
	 * @param model
	 * @return
	 */
	@PostMapping("/save")
	public String saveDoctor(@ModelAttribute Doctor doctor, RedirectAttributes attributes)
	{
		Long id = service.saveDoctor(doctor);
		String message = "Doctor '"+id+"' Created successfully";
		attributes.addAttribute("message", message);
	    return "DoctorRegister";	
	}
	
	/***
	 * 3. Display Data
	 * @param model
	 * @return
	 */
	@GetMapping("/all")
	public String getAllEmployee(Model model, @RequestParam(value = "message",required = false)String message)
	{
		List<Doctor> list = service.getAllDoctors();
		model.addAttribute("list",list);
		model.addAttribute("message", message);
		return "DoctorData";
	}
	
	/***
	 * 4. Delete by Id
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam("id") Long id, Model model,RedirectAttributes attributes)
	{
		String message = null;
		try {
			service.deleteDoctor(id);
		    message = "Doctor '"+id+"' Deleted successfully";
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			message = e.getMessage();
		}
		
		attributes.addAttribute("message", message);
		    return "redirect:DoctorData";
	}
	
	/***
	 * 5. Show edit Page
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam("id") Long id, Model model, RedirectAttributes attributes)
	{
		String page = null;
		try {
			Doctor doctor = service.getOneDoctor(id);
			model.addAttribute("doctor",doctor);
			page = "DoctorEdit";	
		}catch(DoctorNotFoundException e)
		{
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page = "redirect:all";	
		}
		
		return page;
	}
	
   /***
    * 6. Do update
    * @param doctorn
    * @return
    */
	
	@PostMapping("/update")	
	public String updateDoctor(@ModelAttribute Doctor doctor,RedirectAttributes attributes)
	{
		service.updateDoctor(doctor);
		attributes.addAttribute("message", doctor.getId()+",Updated");
		return "redirect:all";
	}
	
	/***
	 * 6. Email and Mobile duplicate Validate(Ajax)
	 */
	
	/***
	 * 7. excel export
	 * 
	 */
}
