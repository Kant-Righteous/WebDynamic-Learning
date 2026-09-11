package utcapitole.miage._026examtd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage._026examtd.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUsername(String username);

}
