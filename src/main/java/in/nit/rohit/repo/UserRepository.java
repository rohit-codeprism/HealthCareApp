package in.nit.rohit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nit.rohit.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {

	Optional<User> findByUsername(String username);
}
