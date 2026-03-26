package utcapitole.miage.tp6_7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp6_7.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

}
