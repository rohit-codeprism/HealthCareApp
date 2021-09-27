package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Doctor;

public interface IDoctorService {
	
	Long saveDoctor(Doctor doctor);
	Doctor getOneDoctor(Long id);
	List<Doctor> getAllDoctors();
	void deleteDoctor(Long id);
	void updateDoctor(Doctor doctor);
	

}
