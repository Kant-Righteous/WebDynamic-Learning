package utcapitole.miage.tp3.tp4.model.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import utcapitole.miage.tp3.tp4.model.Conference;
import utcapitole.miage.tp3.tp4.model.User;

import java.util.List;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public List<Conference> findByTitleConf(String titleConf) {
        return conferenceRepository.findByTitleConf(titleConf);
    }

    public void save(Conference conference) {
        conferenceRepository.save(conference);
    }

    @Transactional
    public boolean updateConferenceDetails(Conference updatedConference) {
        Conference existing = findById(updatedConference.getIdConf());
        if (existing == null) {
            return false;
        }

        existing.setTitleConf(updatedConference.getTitleConf());
        existing.setNbEditionConf(updatedConference.getNbEditionConf());
        existing.setDtStartConf(updatedConference.getDtStartConf());
        existing.setDtEndConf(updatedConference.getDtEndConf());
        existing.setUrlWebsiteConf(updatedConference.getUrlWebsiteConf());
        conferenceRepository.save(existing);
        return true;
    }

    public void deleteById(int idConf) {
        conferenceRepository.deleteById(idConf);
    }

    public Conference findById(int idConf) {
        return conferenceRepository.findById(idConf).orElse(null);
    }

    public boolean addParticipant(int idConf, int userId) {
        Conference conference = findById(idConf);
        User user = userRepository.findById(userId).orElse(null);
        if (conference == null || user == null) {
            return false;
        }

        List<Conference> participatedConferences = user.getParticipatedConferences();
        if (participatedConferences.stream().noneMatch(c -> c.getIdConf() == idConf)) {
            participatedConferences.add(conference);
            userRepository.save(user);
        }
        return true;
    }

    public boolean removeParticipant(int idConf, int userId) {
        Conference conference = findById(idConf);
        User user = userRepository.findById(userId).orElse(null);
        if (conference == null || user == null) {
            return false;
        }

        boolean removed = user.getParticipatedConferences().removeIf(c -> c.getIdConf() == idConf);
        if (removed) {
            userRepository.save(user);
        }
        return true;
    }

    public boolean setOrganizer(int idConf, int userId) {
        Conference conference = findById(idConf);
        User user = userRepository.findById(userId).orElse(null);
        if (conference == null || user == null) {
            return false;
        }

        conference.setOrganizer(user);
        conferenceRepository.save(conference);
        return true;
    }

    public boolean clearOrganizer(int idConf) {
        Conference conference = findById(idConf);
        if (conference == null) {
            return false;
        }

        conference.setOrganizer(null);
        conferenceRepository.save(conference);
        return true;
    }
}
