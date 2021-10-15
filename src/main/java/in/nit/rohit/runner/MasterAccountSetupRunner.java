package in.nit.rohit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.rohit.entity.User;
import in.nit.rohit.service.IUserService;
import in.nit.rohit.service.constant.UserRoles;
import in.nit.rohit.util.UserUtil;

@Component
public class MasterAccountSetupRunner implements CommandLineRunner {

	@Value("${master.user.name}")
	private String displayName;
	
	@Value("${master.user.email}")
	private String username;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil userUtil;
	
	
	@Override
	public void run(String... args) throws Exception {
	if(!userService.findByUsername(username).isPresent())
	{
		User user = new User();
		user.setDisplayName(displayName);
		user.setUsername(username);
		user.setPassword(userUtil.getPwd());
		user.setRole(UserRoles.ADMIN.name());
		userService.saveUser(user);
		// TODO:EMail Service
	}
		
	}

}
