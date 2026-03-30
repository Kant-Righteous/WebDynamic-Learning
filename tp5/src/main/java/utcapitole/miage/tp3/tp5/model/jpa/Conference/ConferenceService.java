package utcapitole.miage.tp3.tp5.model.jpa.Conference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utcapitole.miage.tp3.tp5.model.Conference;
import utcapitole.miage.tp3.tp5.model.Thematique;
import utcapitole.miage.tp3.tp5.model.User;
import utcapitole.miage.tp3.tp5.model.jpa.Thematique.ThematiqueRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ConferenceService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ThematiqueRepository thematiqueRepository;

    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public Conference createConference(Conference conference, List<Integer> thematiqueIds, User organisateur) {
        if (thematiqueIds != null && !thematiqueIds.isEmpty()) {
            List<Thematique> thematiques = thematiqueRepository.findAllById(thematiqueIds);
            conference.setThematiques(thematiques);
        }

        conference.setOrganisateur(organisateur);

        return conferenceRepository.save(conference);
    }

    public List<Conference> getConferencesOrganizedBy(User user) {
        return conferenceRepository.findByOrganizer(user);
    }

    public List<Conference> getConferencesParticipatingIn(User user) {
        return conferenceRepository.findByParticipantsContains(user);
    }

    public boolean canUserJoinConference(Conference conference, User user) {
        if (conference == null || user == null || user.getId() == null) {
            return false;
        }

        if (conference.getOrganizer() != null && user.getId().equals(conference.getOrganizer().getId())) {
            return false;
        }

        return conference.getParticipants().stream()
                .noneMatch(participant -> user.getId().equals(participant.getId()));
    }

    public Set<Integer> getJoinableConferenceIds(User user) {
        Set<Integer> joinableIds = new HashSet<>();
        for (Conference conference : conferenceRepository.findAll()) {
            if (canUserJoinConference(conference, user)) {
                joinableIds.add(conference.getIdconf());
            }
        }
        return joinableIds;
    }

    public boolean joinConference(Integer conferenceId, User user) {
        Conference conf = conferenceRepository.findById(conferenceId).orElse(null);
        if (canUserJoinConference(conf, user)) {
            conf.getParticipants().add(user);
            if (user.getParticipatedConferences().stream().noneMatch(c -> conf.getIdconf().equals(c.getIdconf()))) {
                user.getParticipatedConferences().add(conf);
            }
            conferenceRepository.save(conf);
            return true;
        }

        return false;
    }

    public boolean leaveConference(Integer conferenceId, User user) {
        Conference conf = conferenceRepository.findById(conferenceId).orElse(null);
        if (conf != null && user != null) {
            conf.getParticipants().removeIf(participant -> participant.getId().equals(user.getId()));
            user.getParticipatedConferences().removeIf(c -> c.getIdconf().equals(conf.getIdconf()));
            conferenceRepository.save(conf);
            return true;
        }
        return false;
    }

    public boolean deleteConference(Integer conferenceId, User user) {
        Conference conf = conferenceRepository.findById(conferenceId).orElse(null);
        if (conf != null && conf.getOrganizer() != null && conf.getOrganizer().getId().equals(user.getId())) {
            // Need to first clear references conceptually (JPA sometimes struggles if we just delete directly depending on cascades)
            conf.getParticipants().forEach(p -> p.getParticipatedConferences().removeIf(c -> c.getIdconf().equals(conf.getIdconf())));
            conf.getParticipants().clear();
            conf.getThematiques().clear();
            conferenceRepository.delete(conf);
            return true;
        }
        return false;
    }

}
