package in.nit.rohit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import in.nit.rohit.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);
	
	@Modifying
	@Query("UPDATE User SET password=:encPwd WHERE id=:userId")
	void updateUserPwd(String encPwd, Long userId);
	

	// While Saving the Doctor/Patient Register
	@Query("SELECT COUNT(username) FROM User WHERE username=:email")
	Integer getPatEmailCount(String email);
	
	// While Editing the Doctor/Patient Register
	@Query("SELECT COUNT(username) FROM User WHERE username=:email AND id!=:id")
	Integer getPatEmailCountForEdit(String email, Long id);
	
}
