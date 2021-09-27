package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Doctor;
import in.nit.rohit.exception.DoctorNotFoundException;
import in.nit.rohit.repo.DoctorRepository;
import in.nit.rohit.service.IDoctorService;

@Service
public class DoctorServiceImpl implements IDoctorService {

	@Autowired
	private DoctorRepository repo;
	
	@Override
	public Long saveDoctor(Doctor doctor) {
		
		return repo.save(doctor).getId();
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

}
