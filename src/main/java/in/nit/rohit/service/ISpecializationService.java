package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Specialization;

public interface ISpecializationService {
	
	Long saveSpecialization(Specialization specialization);
	Specialization getOneSpecialization(Long id);
	List<Specialization> getAllSpecialization();
	void deleteSpecialization(Long id);
	void UpdateSpecialization(Specialization specialization);
	public boolean isSpecCodeExist(String specCode);
	public boolean isSpecCodeExistForEdit(String specCode,Long id);
	

}
