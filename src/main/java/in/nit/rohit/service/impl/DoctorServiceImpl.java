 package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Doctor;
import in.nit.rohit.entity.User;
import in.nit.rohit.exception.DoctorNotFoundException;
import in.nit.rohit.repo.DoctorRepository;
import in.nit.rohit.service.IDoctorService;
import in.nit.rohit.service.IUserService;
import in.nit.rohit.service.constant.UserRoles;
import in.nit.rohit.util.MailUtil;
import in.nit.rohit.util.UserUtil;
import in.nit.rohit.util.myCollectionsUtil;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Autowired
    private IUserService userService;
	
	@Autowired
	private UserUtil util;
	
	@Autowired 
	private MailUtil mailUtil;
	
	@Override
	public Long saveDoctor(Doctor doctor) {
		Long id = repo.save(doctor).getId();
		if(id != null)
		{
			String pwd = util.getPwd();
			User user = new User();
			user.setDisplayName(doctor.getFirstName()+" "+doctor.getLastName());
			user.setUsername(doctor.getEmail());
			user.setPassword(pwd);
			user.setRole(UserRoles.DOCTOR.name());
			Long genId =  userService.saveUser(user);
			if(genId != null)
				new Thread(new Runnable() {
					public void run()
					{
						String text = "Your Username is : "+doctor.getEmail()+"And Password is:"+pwd;
						mailUtil.send(doctor.getEmail(),"Doctor Added",text);
						
					}
					
				}).start();
			
		}
		
		
		return id;
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		/*
		Optional<Doctor> opt = repo.findById(id);
		if(opt.isPresent())
		{
			Doctor doctor = opt.get();
			return doctor;
		}
		// TODO: else show the exception that Doctor is not present
		 * */
		return repo.findById(id).orElseThrow(()-> new DoctorNotFoundException(id+", not exist"));
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		return repo.findAll();
	}

	@Override
	public void deleteDoctor(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		if(repo.existsById(doctor.getId()))
		repo.save(doctor);
		
		else throw new DoctorNotFoundException(doctor.getId()+",not exist ");
		
		
	}

	@Override
	public Map<Long,String> getDoctorIdAndName() {
		List<Object[]> list = repo.getDoctorIdAndName();
		
		return myCollectionsUtil.convertToMapIndex(list);
	}

	@Override
	public List<Doctor> findDoctorBySpecId(Long specId) {
		 
		return repo.findDoctorBySpecId(specId);
	}

	@Override
	public Long getDoctorCount() {
		
		return repo.count();
	}

}
