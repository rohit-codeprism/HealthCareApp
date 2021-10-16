package in.nit.rohit.service.impl;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.User;
import in.nit.rohit.repo.UserRepository;
import in.nit.rohit.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public Long saveUser(User user) {
		// Read generated password
		String pwd = user.getPassword();
		// encode password
		 String encPwd = passwordEncoder.encode(pwd);
		//set back to object 
		user.setPassword(encPwd);
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		return repo.findByUsername(username);
	}
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		Optional<User> opt = findByUsername(username);
		if(!opt.isPresent())
			 throw new UsernameNotFoundException(username);
		else
		{
			 User user = opt.get();
		     return new org.springframework.security.core.userdetails.User(username,user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
		}
		
		
	}

}
