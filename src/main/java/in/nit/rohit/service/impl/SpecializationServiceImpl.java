package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Specialization;
import in.nit.rohit.repo.SpecializationRepository;
import in.nit.rohit.service.ISpecializationService;

@Service
public class SpecializationServiceImpl implements ISpecializationService {
	
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Integer saveSpecialization(Specialization specialization) {
		
		return repo.save(specialization).getId();
	}

	@Override
	public Specialization getOneSpecialization(Integer id) {
		Specialization specialization;
		Optional<Specialization> opt = repo.findById(id);
		if(opt.isPresent())
		{
			specialization = opt.get();
			return specialization;
		}
		// TODO: else if terurn exception 	
		return null;
	}

	@Override
	public List<Specialization> getAllSpecialization() {
		List<Specialization> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteSpecialization(Integer id) {
		repo.deleteById(id);
		
	}

	@Override
	public void UpdateSpecialization(Specialization specialization) {
		repo.save(specialization);
		
	}

}
