package in.nit.rohit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import in.nit.rohit.entity.User;
import in.nit.rohit.service.IUserService;
import in.nit.rohit.service.constant.UserRoles;
import in.nit.rohit.util.MailUtil;
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
	
	@Autowired
	private MailUtil mailUtil;
	
	
	@Override
	public void run(String... args) throws Exception {
	if(!userService.findByUsername(username).isPresent())
	{
		String pwd = userUtil.getPwd();
		User user = new User();
		user.setDisplayName(displayName);
		user.setUsername(username);
		user.setPassword(pwd);
		user.setRole(UserRoles.ADMIN.name());
		Long genId  = userService.saveUser(user);
		if(genId != null)
		{
			new Thread(new Runnable() {
				public void run() {
					String text  = "Your username is : " +username+"And your Password is : "+pwd;
					mailUtil.send(username, "User Added", text);
				}
			}).start();
		}
		
	}
		
	}

}
