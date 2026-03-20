package utcapitole.miage.tp3.tp4.model.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import utcapitole.miage.tp3.tp4.model.Conference;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {

    List<Conference> findByTitleConf(String titleConf);

}
