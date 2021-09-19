package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Doctor;

public interface IDoctorService {
	
	Integer saveDoctor(Doctor doctor);
	Doctor getOneDoctor(Integer id);
	List<Doctor> getAllDoctors();
	void deleteDoctor(Integer id);
	void updateDoctor(Doctor doctor);
	

}
