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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.exception.SpecializationNotFoundException;
import in.nit.rohit.service.ISpecializationService;
import in.nit.rohit.view.SpecializationExcelView;
import in.nit.rohit.view.SpecializationPdfView;

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
	 *3. Display all Specialization
	 */
	@GetMapping("/all")
	public String  viewAllSpecialization(Model model, @RequestParam(value = "message", required = false) String message)
	{
		List<Specialization> list = service.getAllSpecialization();
		model.addAttribute("list",list);
		model.addAttribute("message",message);
			
		return "SpecializationData";
	}
	
	/***
	 *4. Delete by Id
	 */
	@GetMapping("/delete")
	public String deleteSpecialization(@RequestParam Long id, RedirectAttributes attributes)
	{
		try {
			// Call service
			service.deleteSpecialization(id);
			
			// one success message 
			attributes.addAttribute("message","Specialization ("+id+") Deleted successfully");
			
		}catch(SpecializationNotFoundException e){
		    e.printStackTrace();
		    attributes.addAttribute("message", e.getMessage());
		}
		 
		return "redirect:all";
		
		
	}
	
	/***
	 *5. Fetch Data into edit page
	 *End user clickes on Link, May enter ID manually
	 * if Entered id is present in DB
	 *  > Load Row as object 
	 *  > send to edit
	 *  > form in form
	 * Else
	 *  > Redirect to all(Data Page)
	 *  > Show Error message(Not Found )
	 * @return
	 */
	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model, RedirectAttributes attributes)
	{
		String page = null;
		try {
			
			//get one specialization...load objct from DB
			Specialization spec = service.getOneSpecialization(id);
			// sent it to UI
			model.addAttribute("specialization", spec);
			page = "SpecializationEdit";
		}catch(SpecializationNotFoundException e){
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			page =  "redirect:all";
			
		}
		return page;
		

	}
	
	/***
	 * 6.Do update....Read data from UI page as  ModelAttribute
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
	
	/***
	 * 7. Read code and check wit service 
	 * Do not  return the page name 
	 * Return message back to UI
	 * to the same page where the request came 
	 * 
	 */
	@GetMapping("/checkCode")
	@ResponseBody
	public   String validateSpecCode(@RequestParam String code,@RequestParam Long id)
	{
		 String message="";
		 if(id == 0 && service.isSpecCodeExist(code)) {
			 message = code +", already exist";
		 }else if(id !=0 && service.isSpecCodeExistForEdit(code, id)) {
			 message = code +",Already exist ";
		 }
		 return message; // this is not a view name(it is message)
	}
	
	/***
	 *  8. Export data to excel file
	 */
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationExcelView());
		
		// Read data from DB 
		List<Specialization> list = service.getAllSpecialization();
		// Send to Excel Impl class
		m.addObject("list",list);
		return m;
		
	}
	
	/***
	 * 9. export to pdf
	 */
	@GetMapping("/pdf")
	public ModelAndView exportToPdf() {
		ModelAndView m = new ModelAndView();
		m.setView(new SpecializationPdfView());
		
		// Read data from DB 
	       List<Specialization> list = service.getAllSpecialization();
	    // Send to Pdf Impl class
		   m.addObject("list",list);
		return m;
	}

}
