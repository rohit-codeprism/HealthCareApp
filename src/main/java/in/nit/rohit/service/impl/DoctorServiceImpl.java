package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Doctor;
import in.nit.rohit.repo.DoctorRepository;
import in.nit.rohit.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Integer saveDoctor(Doctor doctor) {
		
		return repo.save(doctor).getId();
	}

	@Override
	public Doctor getOneDoctor(Integer id) {
		Optional<Doctor> opt = repo.findById(id);
		if(opt.isPresent())
		{
			Doctor doctor = opt.get();
			return doctor;
		}
		// TODO: else show the exception that Doctor is not present 
		return null;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		
		return repo.findAll();
	}

	@Override
	public void deleteDoctor(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public void updateDoctor(Doctor doctor) {
		repo.save(doctor);
		
	}

}
