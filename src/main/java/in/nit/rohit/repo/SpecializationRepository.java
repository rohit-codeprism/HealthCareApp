package in.nit.rohit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nit.rohit.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization,Long> {

	@Query("SELECT COUNT(specCode) FROM Specialization WHERE specCode=:specCode")
	Integer getSpecializationSpecCodeCount(String specCode);
}
