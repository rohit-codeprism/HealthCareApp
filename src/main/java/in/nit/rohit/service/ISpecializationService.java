package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Specialization;

public interface ISpecializationService {
	
	Integer saveSpecialization(Specialization specialization);
	Specialization getOneSpecialization(Integer id);
	List<Specialization> getAllSpecialization();
	void deleteSpecialization(Integer id);
	void UpdateSpecialization(Specialization specialization);
	

}
