package in.nit.rohit.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.nit.rohit.entity.Appointment;
import in.nit.rohit.repo.AppointmentRepository;
import in.nit.rohit.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	private AppointmentRepository repo;

	@Override
	@Transactional
	public Long saveAppointment(Appointment appointment) {
		
		return repo.save(appointment).getId();
	}

	@Override
	@Transactional
	public void updateAppointment(Appointment appointment) {
		
		repo.save(appointment);
		/*
		if(repo.existsById(appointment.getId()))
			repo.save(appointment);
		else throw new AppointmentNotFoundException(appointment.getId()+": does not exist");
		*/
		
		
	}
	
	@Override
	@Transactional
	public void deleteAppointment(Long id) {
		
		repo.deleteById(id);
		/*
		if(repo.existsById(id))
			repo.deleteById(id);
		else throw new AppointmentNotFoundException(id+": does Not exist ");
		*/
	}
	
	@Override
	@Transactional(readOnly = true)
	public Appointment getOneAppointment(Long id) {
	    
		return repo.findById(id).get();
		//return repo.findById(id).orElseThrow(()-> new AppointmentNotFoundException(id+ ":Does not Exist"));
	}



	@Override
	@Transactional(readOnly = true)
	public List<Appointment> getAllAppointment() {
		
		return repo.findAll();
	}

	
	
	
	@Override
	public List<Object[]> getAppointmentsByDoctorId(Long docId) {
		
		return repo.getAppoinmentsByDoctor(docId);
	}
	
	@Override
	public List<Object[]> getAppointmentsByDoctorEmail(String userName) {
		
		return repo.getAppoinmentsByDoctorEmail(userName);
	}

	@Override
	@Transactional
	public void updateSlotCountForAppointment(Long id, int count) {
		repo.updateSlotCountForAppoinment(id, count);
		
	}

	@Override
	public long getAppointmentCount() {
		
		return repo.count();
	}

	


}
