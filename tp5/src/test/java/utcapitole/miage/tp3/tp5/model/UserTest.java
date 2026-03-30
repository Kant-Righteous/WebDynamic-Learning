package utcapitole.miage.tp3.tp5.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void constructor() {
        User user = new User();

        assertNotNull(user.getOrganizedConferences());
        assertTrue(user.getOrganizedConferences().isEmpty());
        assertNotNull(user.getParticipatedConferences());
        assertTrue(user.getParticipatedConferences().isEmpty());
    }

    @Test
    void getId() {
        User user = new User();
        user.setId(1);

        assertEquals(1, user.getId());
    }

    @Test
    void setId() {
        User user = new User();

        user.setId(2);

        assertEquals(2, user.getId());
    }

    @Test
    void getName() {
        User user = new User();
        user.setName("Alice");

        assertEquals("Alice", user.getName());
    }

    @Test
    void setName() {
        User user = new User();

        user.setName("Bob");

        assertEquals("Bob", user.getName());
    }

    @Test
    void getEmail() {
        User user = new User();
        user.setEmail("alice@example.com");

        assertEquals("alice@example.com", user.getEmail());
    }

    @Test
    void setEmail() {
        User user = new User();

        user.setEmail("bob@example.com");

        assertEquals("bob@example.com", user.getEmail());
    }

    @Test
    void getPassword() {
        User user = new User();
        user.setPassword("secret");

        assertEquals("secret", user.getPassword());
    }

    @Test
    void setPassword() {
        User user = new User();

        user.setPassword("top-secret");

        assertEquals("top-secret", user.getPassword());
    }

    @Test
    void getStatut() {
        User user = new User();
        Statut statut = new Statut();
        user.setStatut(statut);

        assertSame(statut, user.getStatut());
    }

    @Test
    void setStatut() {
        User user = new User();
        Statut statut = new Statut();

        user.setStatut(statut);

        assertSame(statut, user.getStatut());
    }

    @Test
    void getOrganizedConferences() {
        User user = new User();
        List<Conference> conferences = new ArrayList<>();
        conferences.add(new Conference());
        user.setOrganizedConferences(conferences);

        assertSame(conferences, user.getOrganizedConferences());
    }

    @Test
    void setOrganizedConferences() {
        User user = new User();
        List<Conference> conferences = List.of(new Conference(), new Conference());

        user.setOrganizedConferences(conferences);

        assertSame(conferences, user.getOrganizedConferences());
    }

    @Test
    void getParticipatedConferences() {
        User user = new User();
        List<Conference> conferences = new ArrayList<>();
        conferences.add(new Conference());
        user.setParticipatedConferences(conferences);

        assertSame(conferences, user.getParticipatedConferences());
    }

    @Test
    void setParticipatedConferences() {
        User user = new User();
        List<Conference> conferences = List.of(new Conference());

        user.setParticipatedConferences(conferences);

        assertSame(conferences, user.getParticipatedConferences());
    }
}
