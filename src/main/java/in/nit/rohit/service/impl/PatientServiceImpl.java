package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Patient;
import in.nit.rohit.repo.PatientRepository;
import in.nit.rohit.service.IPatientService;
 
@Service
public class PatientServiceImpl implements IPatientService {
	
	@Autowired
	private PatientRepository repo;

	@Override
	public Long savePatient(Patient patient) {
		return repo.save(patient).getId() ;
	}

	@Override
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
	public List<Patient> getAllPatient() {
		return repo.findAll();
	}

	@Override
	public void deletePatient(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public void updatePatient(Patient patient) {
		repo.save(patient);
		
	}

}
