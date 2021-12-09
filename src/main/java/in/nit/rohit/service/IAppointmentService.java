package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Appointment;

public interface IAppointmentService {
	
	 public Long saveAppointment(Appointment appointment);
	 public void updateAppointment(Appointment appointment);
	 public void deleteAppointment(Long id);
	 public Appointment  getOneAppointment(Long id);
	 public List<Appointment> getAllAppointment();
	 public List<Object[]> getAppointmentsByDoctorId(Long docId);
	 public List<Object[]> getAppointmentsByDoctorEmail(String userName);
	 public void updateSlotCountForAppointment(Long id, int count);
	 public long getAppointmentCount();
	
	 
	 

}
