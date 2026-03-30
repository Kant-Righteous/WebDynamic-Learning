package utcapitole.miage.tp3.tp5.model.jpa.Conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import utcapitole.miage.tp3.tp5.model.Conference;
import utcapitole.miage.tp3.tp5.model.Thematique;
import utcapitole.miage.tp3.tp5.model.User;
import utcapitole.miage.tp3.tp5.model.jpa.Thematique.ThematiqueRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConferenceServiceTest {

    @Mock
    private ConferenceRepository conferenceRepository;

    @Mock
    private ThematiqueRepository thematiqueRepository;

    @InjectMocks
    private ConferenceService conferenceService;

    @Test
    void findAll() {
        List<Conference> conferences = List.of(new Conference(), new Conference());
        when(conferenceRepository.findAll()).thenReturn(conferences);

        assertSame(conferences, conferenceService.findAll());
    }

    @Test
    void createConferenceSetsThematiquesAndOrganizerBeforeSaving() {
        Conference conference = new Conference();
        User organizer = new User();
        List<Integer> thematiqueIds = List.of(1, 2);
        List<Thematique> thematiques = List.of(new Thematique(), new Thematique());
        when(thematiqueRepository.findAllById(thematiqueIds)).thenReturn(thematiques);
        when(conferenceRepository.save(conference)).thenReturn(conference);

        Conference savedConference = conferenceService.createConference(conference, thematiqueIds, organizer);

        assertSame(conference, savedConference);
        assertSame(thematiques, conference.getThematiques());
        assertSame(organizer, conference.getOrganizer());
        verify(thematiqueRepository).findAllById(thematiqueIds);
        verify(conferenceRepository).save(conference);
    }

    @Test
    void createConferenceSkipsThematiqueLookupWhenIdsAreMissing() {
        Conference conference = new Conference();
        User organizer = new User();
        when(conferenceRepository.save(conference)).thenReturn(conference);

        Conference savedConference = conferenceService.createConference(conference, null, organizer);

        assertSame(conference, savedConference);
        assertSame(organizer, conference.getOrganizer());
        assertTrue(conference.getThematiques().isEmpty());
        verify(thematiqueRepository, never()).findAllById(any());
    }

    @Test
    void getConferencesOrganizedBy() {
        User user = new User();
        List<Conference> conferences = List.of(new Conference());
        when(conferenceRepository.findByOrganizer(user)).thenReturn(conferences);

        assertSame(conferences, conferenceService.getConferencesOrganizedBy(user));
    }

    @Test
    void getConferencesParticipatingIn() {
        User user = new User();
        List<Conference> conferences = List.of(new Conference());
        when(conferenceRepository.findByParticipantsContains(user)).thenReturn(conferences);

        assertSame(conferences, conferenceService.getConferencesParticipatingIn(user));
    }

    @Test
    void canUserJoinConferenceReturnsFalseWhenConferenceIsNull() {
        assertFalse(conferenceService.canUserJoinConference(null, new User()));
    }

    @Test
    void canUserJoinConferenceReturnsFalseWhenUserIsNull() {
        assertFalse(conferenceService.canUserJoinConference(new Conference(), null));
    }

    @Test
    void canUserJoinConferenceReturnsFalseWhenUserIdIsNull() {
        assertFalse(conferenceService.canUserJoinConference(new Conference(), new User()));
    }

    @Test
    void canUserJoinConferenceReturnsFalseForOrganizer() {
        User organizer = new User();
        organizer.setId(1);
        Conference conference = new Conference();
        conference.setOrganizer(organizer);

        assertFalse(conferenceService.canUserJoinConference(conference, organizer));
    }

    @Test
    void canUserJoinConferenceReturnsFalseWhenAlreadyParticipant() {
        User user = new User();
        user.setId(2);
        Conference conference = new Conference();
        conference.setParticipants(new ArrayList<>(List.of(user)));

        assertFalse(conferenceService.canUserJoinConference(conference, user));
    }

    @Test
    void canUserJoinConferenceReturnsTrueWhenUserCanJoin() {
        User organizer = new User();
        organizer.setId(1);
        User user = new User();
        user.setId(2);
        Conference conference = new Conference();
        conference.setOrganizer(organizer);
        conference.setParticipants(new ArrayList<>());

        assertTrue(conferenceService.canUserJoinConference(conference, user));
    }

    @Test
    void getJoinableConferenceIdsReturnsOnlyJoinableConferenceIds() {
        User organizer = new User();
        organizer.setId(1);
        User user = new User();
        user.setId(2);

        Conference joinableConference = new Conference();
        joinableConference.setIdconf(10);
        joinableConference.setOrganizer(organizer);
        joinableConference.setParticipants(new ArrayList<>());

        Conference ownedConference = new Conference();
        ownedConference.setIdconf(11);
        ownedConference.setOrganizer(user);
        ownedConference.setParticipants(new ArrayList<>());

        Conference joinedConference = new Conference();
        joinedConference.setIdconf(12);
        joinedConference.setOrganizer(organizer);
        joinedConference.setParticipants(new ArrayList<>(List.of(user)));

        when(conferenceRepository.findAll()).thenReturn(List.of(joinableConference, ownedConference, joinedConference));

        Set<Integer> joinableIds = conferenceService.getJoinableConferenceIds(user);

        assertEquals(Set.of(10), joinableIds);
    }

    @Test
    void joinConferenceAddsUserAndConferenceWhenJoinIsAllowed() {
        User organizer = new User();
        organizer.setId(1);
        User user = new User();
        user.setId(2);
        user.setParticipatedConferences(new ArrayList<>());

        Conference conference = new Conference();
        conference.setIdconf(10);
        conference.setOrganizer(organizer);
        conference.setParticipants(new ArrayList<>());

        when(conferenceRepository.findById(10)).thenReturn(Optional.of(conference));

        assertTrue(conferenceService.joinConference(10, user));
        assertTrue(conference.getParticipants().contains(user));
        assertTrue(user.getParticipatedConferences().contains(conference));
        verify(conferenceRepository).save(conference);
    }

    @Test
    void joinConferenceDoesNotDuplicateParticipatedConference() {
        User organizer = new User();
        organizer.setId(1);
        User user = new User();
        user.setId(2);

        Conference conference = new Conference();
        conference.setIdconf(10);
        conference.setOrganizer(organizer);
        conference.setParticipants(new ArrayList<>());
        user.setParticipatedConferences(new ArrayList<>(List.of(conference)));

        when(conferenceRepository.findById(10)).thenReturn(Optional.of(conference));

        assertTrue(conferenceService.joinConference(10, user));
        assertEquals(1, user.getParticipatedConferences().size());
        assertTrue(conference.getParticipants().contains(user));
        verify(conferenceRepository).save(conference);
    }

    @Test
    void joinConferenceReturnsFalseWhenJoinIsNotAllowed() {
        User user = new User();
        user.setId(1);
        Conference conference = new Conference();
        conference.setOrganizer(user);
        when(conferenceRepository.findById(10)).thenReturn(Optional.of(conference));

        assertFalse(conferenceService.joinConference(10, user));
        verify(conferenceRepository, never()).save(any(Conference.class));
    }
}
