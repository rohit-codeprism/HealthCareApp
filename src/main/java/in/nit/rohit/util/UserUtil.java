package in.nit.rohit.util;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	
	public String getPwd() {
		
		return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
	}

}
