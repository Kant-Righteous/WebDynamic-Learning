package utcapitole.miage.tp3.tp5.model.jpa.Conference;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp3.tp5.model.Conference;
import utcapitole.miage.tp3.tp5.model.User;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    List<Conference> findByOrganizer(User organizer);

    List<Conference> findByParticipantsContains(User participant);
}
