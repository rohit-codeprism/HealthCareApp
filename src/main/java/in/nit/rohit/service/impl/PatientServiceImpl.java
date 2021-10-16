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
import in.nit.rohit.util.MailUtil;
import in.nit.rohit.util.UserUtil;
 
@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository repo;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private UserUtil util;
	
	@Autowired
	private MailUtil mailUtil;

	@Override
	@Transactional
	public Long savePatient(Patient patient) {
		Long id = repo.save(patient).getId();
		if(id != null)
		{
			String pwd = util.getPwd();
			User user = new User();
			user.setDisplayName(patient.getFirstName()+" "+patient.getLastName());
			user.setUsername(patient.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.PATIENT.name());
			Long genId = userService.saveUser(user); 
			if(genId != null)
				new Thread(new Runnable() {
					public void run() {
						 
						 String text = "Your uesername is: "+patient.getEmail()+" And Password is:"+ pwd;
				         mailUtil.send(patient.getEmail(), "Patient Added", text);
						
					}
				}).start();
			
			
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
