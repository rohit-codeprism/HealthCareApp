package in.nit.rohit.service;

import java.util.List;

import in.nit.rohit.entity.Appointment;

public interface IAppointmentService {
	
	 public Long saveAppointment(Appointment appointment);
	 public List<Appointment> getAllAppointment();
	 public Appointment  getOneAppointment(Long id);
	 public void deleteAppointment(Long id);
	 public void updateAppointment(Appointment appointment);
	 
	 

}
