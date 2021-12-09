package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Patient;

public interface IPatientService {
	
	Long savePatient(Patient patient);
	Patient getOnePatient(Long id);
	Patient getOneByEmail(String email);
	List<Patient> getAllPatient();
	void deletePatient(Long id);
	void updatePatient(Patient patient);
	public Long getPatientCount();
	

}
