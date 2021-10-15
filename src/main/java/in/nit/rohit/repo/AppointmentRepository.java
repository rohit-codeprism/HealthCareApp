package in.nit.rohit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.rohit.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

}
