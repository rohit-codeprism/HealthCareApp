package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Patient;
import in.nit.rohit.entity.User;
import in.nit.rohit.repo.PatientRepository;
import in.nit.rohit.service.IPatientService;
import in.nit.rohit.service.IUserService;
import in.nit.rohit.service.constant.UserRoles;
import in.nit.rohit.util.UserUtil;
 
@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil util;

	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if(id != null)
		{
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			user.setPassword(util.getPwd());
			user.setRole(UserRoles.PATIENT.name());
			userService.saveUser(user); 
			// TODO : Email part is pending 
		}
		return id ;
	} 

	@Override
	@Transactional
	public Patient getOnePatient(Long id) {
		Optional<Patient> opt = repo.findById(id);
		if(opt.isPresent())
		{
			Patient patient = opt.get();
			return patient;
		}
		// TODO: else show the exception that Patient does nor esixt
		return null;
	}

	@Override
	@Transactional
	public List<Patient> getAllPatient() {
		return repo.findAll();
	}

	@Override
	@Transactional
	public void deletePatient(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	@Transactional
	public void updatePatient(Patient patient) {
		repo.save(patient);
		
	}

}
