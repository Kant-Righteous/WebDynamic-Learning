package utcapitole.miage.tp3.tp5.model.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp3.tp5.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

}
