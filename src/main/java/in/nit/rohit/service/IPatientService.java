package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Patient;

public interface IPatientService {
	
	Integer savePatient(Patient patient);
	Patient getOnePatient(Integer id);
	List<Patient> getAllPatient();
	void deletePatient(Integer id);
	void updatePatient(Patient patient);

}
