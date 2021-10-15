package in.nit.rohit.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.rohit.entity.Appointment;
import in.nit.rohit.exception.AppointmentNotFoundException;
import in.nit.rohit.repo.AppointmentRepository;
import in.nit.rohit.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	@Override
	public Long saveAppointment(Appointment appointment) {
		
		return repo.save(appointment).getId();
	}

	@Override
	public List<Appointment> getAllAppointment() {
		
		return repo.findAll();
	}

	@Override
	public Appointment getOneAppointment(Long id) {
	    
		return repo.findById(id).orElseThrow(()-> new AppointmentNotFoundException(id+ ":Does not Exist"));
	}

	@Override
	public void deleteAppointment(Long id) {
		if(repo.existsById(id))
			repo.deleteById(id);
		else throw new AppointmentNotFoundException(id+": does Not exist ");
		
	}

	@Override
	public void updateAppointment(Appointment appointment) {
		if(repo.existsById(appointment.getId()))
			repo.save(appointment);
		else throw new AppointmentNotFoundException(appointment.getId()+": does not exist");
		
		
	}

}
