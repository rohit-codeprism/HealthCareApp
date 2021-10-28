package in.nit.rohit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.rohit.entity.Appointment;


public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

	@Query("SELECT aptn.date, aptn.noOfSlots , aptn.fee FROM Appointment aptn INNER JOIN aptn.doctor as doctor WHERE doctor.id=:docId")
	public List<Object[]> getAppointmentByDoctorId(Long docId);
}
 