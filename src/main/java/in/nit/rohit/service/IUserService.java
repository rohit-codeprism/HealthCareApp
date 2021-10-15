package in.nit.rohit.service;

import java.util.Optional;

import in.nit.rohit.entity.User;

public interface IUserService {
	
	Long saveUser(User user); 
	Optional<User> findByUsername(String username);
	
	
	

}
