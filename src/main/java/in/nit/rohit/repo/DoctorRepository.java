package in.nit.rohit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.rohit.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

}
