package in.nit.rohit.controller;

import java.security.Principal;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.nit.rohit.entity.User;
import in.nit.rohit.service.IUserService;
import in.nit.rohit.util.MailUtil;
import in.nit.rohit.util.UserUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil util;
	
	@Autowired
	private MailUtil mailUtil;
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "UserLogin";
	}
	
	@GetMapping("/profile")
	public String showProfile() {
		
		return "UserProfile";
	}
	
	@GetMapping("/setup")
	public String setUp(HttpSession session, Principal p)
	{
		// Read Current Username
		String username = p.getName(); 
		
		//Load User Object 
		User user = userService.findByUsername(username).get();
		
		//Store in Http Session
		session.setAttribute("userOb",user);
		
		return "UserHome";
	}
	
	@GetMapping("/showPwdUpdate")
	public String showPwdUpdate()
	{
		return "UserPwdUpdate";
	}
	
	@PostMapping("/pwdupdate")
	public String updatePassword(@RequestParam String password, HttpSession session, Model model) {
		
		// Read current user from the session
		User user = (User) session.getAttribute("userOb");
		
		//Read userid
		Long userId = user.getId();
		
		// make service call
		userService.updateUserPwd(password, userId);
		
		// TODO: Email Task
		if(userId != null)
		{
			new Thread(new Runnable(){
				public void run() {
					mailUtil.send(user.getUsername(),"Password Changed Successfully", password);
				}
			}).start();	
		}
		
		
		model.addAttribute("message","Password Update");
		
		return "UserPwdUpdate";
		
	}
	
	@GetMapping("/showForget")
	public String showForgetPassword() {
		return "UserNewPwdGen";
	}
	
	@GetMapping("/genNewPwd")
	public String genNewPassword(@RequestParam String email, Model model) {
		
		// check if user is present with the email or not
		Optional<User> opt = userService.findByUsername(email);
		if(opt.isPresent())
		{
			// read user object
			User user = opt.get();
			
			// Generate new password
			String pwd = util.getPwd();
			
			// Encode the passowrd in update in DB
			userService.updateUserPwd(pwd,user.getId());
			
			// send Message to UI
			model.addAttribute("message","password has been updated successfully" );
			
			// send email to user
			if(user.getId() != null)
			{
			    new Thread(new Runnable() {
			    	public void run() {
			    		
			    		String text = "Your Username is :"+user.getUsername() + "And Your Password is:" +pwd;
			    		mailUtil.send(user.getUsername(), "Password Updated Successfully", text);
			    		
			    	}
			    	
			    }).start();
			}
			
		}else {
			model.addAttribute("message", "User Not Found");
		}
		
		return "UserNewGenPwd";
		
		
	}
}
