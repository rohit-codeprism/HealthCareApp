package in.nit.rohit.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.User;
import in.nit.rohit.repo.UserRepository;
import in.nit.rohit.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	
	@Override
	public Long saveUser(User user) {
		
		return repo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		
		return repo.findByUsername(username);
	}

}
