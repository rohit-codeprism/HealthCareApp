package in.nit.rohit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nit.rohit.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment,Long>{
	
    //AND aptm.date>= current_date"  
	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.id FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.id=:docId AND aptm.noOfSlots>0")
	public List<Object[]> getAppoinmentsByDoctor(Long docId);
	
	@Query("SELECT aptm.date, aptm.noOfSlots, aptm.fee, aptm.details FROM Appointment aptm INNER JOIN aptm.doctor as doctor WHERE doctor.email=:userName AND aptm.noOfSlots>0")
	public List<Object[]> getAppoinmentsByDoctorEmail(String userName);
	
	@Modifying
	@Query("UPDATE Appointment SET noOfSlots = noOfSlots + :count WHERE id=:id")
	void updateSlotCountForAppoinment(Long id,int count);

	
}
 