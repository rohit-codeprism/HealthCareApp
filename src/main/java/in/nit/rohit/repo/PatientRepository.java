package in.nit.rohit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.rohit.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
	
	Optional<Patient> findByEmail(String email);

}
