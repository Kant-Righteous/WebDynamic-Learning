package utcapitole.miage.tp3.tp5.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ConferenceTest {

    @Test
    void constructor() {
        Conference conference = new Conference();

        assertNotNull(conference.getParticipants());
        assertTrue(conference.getParticipants().isEmpty());
        assertNotNull(conference.getThematiques());
        assertTrue(conference.getThematiques().isEmpty());
    }

    @Test
    void getIdconf() {
        Conference conference = new Conference();
        conference.setIdconf(1);

        assertEquals(1, conference.getIdconf());
    }

    @Test
    void setIdconf() {
        Conference conference = new Conference();

        conference.setIdconf(2);

        assertEquals(2, conference.getIdconf());
    }

    @Test
    void getTitleconf() {
        Conference conference = new Conference();
        conference.setTitleconf("Devoxx");

        assertEquals("Devoxx", conference.getTitleconf());
    }

    @Test
    void setTitleconf() {
        Conference conference = new Conference();

        conference.setTitleconf("JUG Summer Camp");

        assertEquals("JUG Summer Camp", conference.getTitleconf());
    }

    @Test
    void getNbeditionconf() {
        Conference conference = new Conference();
        conference.setNbeditionconf(10);

        assertEquals(10, conference.getNbeditionconf());
    }

    @Test
    void setNbeditionconf() {
        Conference conference = new Conference();

        conference.setNbeditionconf(12);

        assertEquals(12, conference.getNbeditionconf());
    }

    @Test
    void getDtstartconf() {
        Conference conference = new Conference();
        LocalDate startDate = LocalDate.of(2026, 3, 21);
        conference.setDtstartconf(startDate);

        assertEquals(startDate, conference.getDtstartconf());
    }

    @Test
    void setDtstartconf() {
        Conference conference = new Conference();
        LocalDate startDate = LocalDate.of(2026, 4, 1);

        conference.setDtstartconf(startDate);

        assertEquals(startDate, conference.getDtstartconf());
    }

    @Test
    void getDtendconf() {
        Conference conference = new Conference();
        LocalDate endDate = LocalDate.of(2026, 3, 25);
        conference.setDtendconf(endDate);

        assertEquals(endDate, conference.getDtendconf());
    }

    @Test
    void setDtendconf() {
        Conference conference = new Conference();
        LocalDate endDate = LocalDate.of(2026, 4, 3);

        conference.setDtendconf(endDate);

        assertEquals(endDate, conference.getDtendconf());
    }

    @Test
    void getUrlwebsiteconf() {
        Conference conference = new Conference();
        conference.setUrlwebsiteconf("https://example.com");

        assertEquals("https://example.com", conference.getUrlwebsiteconf());
    }

    @Test
    void setUrlwebsiteconf() {
        Conference conference = new Conference();

        conference.setUrlwebsiteconf("https://conference.test");

        assertEquals("https://conference.test", conference.getUrlwebsiteconf());
    }

    @Test
    void getOrganizer() {
        Conference conference = new Conference();
        User organizer = new User();
        conference.setOrganizer(organizer);

        assertSame(organizer, conference.getOrganizer());
    }

    @Test
    void setOrganizer() {
        Conference conference = new Conference();
        User organizer = new User();

        conference.setOrganizer(organizer);

        assertSame(organizer, conference.getOrganizer());
    }

    @Test
    void getParticipants() {
        Conference conference = new Conference();
        List<User> participants = new ArrayList<>();
        participants.add(new User());
        conference.setParticipants(participants);

        assertSame(participants, conference.getParticipants());
    }

    @Test
    void setParticipants() {
        Conference conference = new Conference();
        List<User> participants = List.of(new User(), new User());

        conference.setParticipants(participants);

        assertSame(participants, conference.getParticipants());
    }

    @Test
    void getThematiques() {
        Conference conference = new Conference();
        List<Thematique> thematiques = new ArrayList<>();
        thematiques.add(new Thematique());
        conference.setThematiques(thematiques);

        assertSame(thematiques, conference.getThematiques());
    }

    @Test
    void setThematiques() {
        Conference conference = new Conference();
        List<Thematique> thematiques = List.of(new Thematique(), new Thematique());

        conference.setThematiques(thematiques);

        assertSame(thematiques, conference.getThematiques());
    }

    @Test
    void setOrganisateur() {
        Conference conference = new Conference();
        User organizer = new User();

        conference.setOrganisateur(organizer);

        assertSame(organizer, conference.getOrganizer());
    }
}
