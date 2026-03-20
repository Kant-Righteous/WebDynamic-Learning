package utcapitole.miage.tp3.tp4.model.jpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp3.tp4.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = {"organizedConferences"})
    List<User> findAll();

    @EntityGraph(attributePaths = {"participatedConferences"})
    Optional<User> findWithParticipatedConferencesById(Integer id);
}
