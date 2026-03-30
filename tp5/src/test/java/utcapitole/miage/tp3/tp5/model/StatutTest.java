package utcapitole.miage.tp3.tp5.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatutTest {

    @Test
    void getCodeStatut() {
        Statut statut = new Statut();
        statut.setCodeStatut(1);

        assertEquals(1, statut.getCodeStatut());
    }

    @Test
    void setCodeStatut() {
        Statut statut = new Statut();

        statut.setCodeStatut(2);

        assertEquals(2, statut.getCodeStatut());
    }

    @Test
    void getNomStatut() {
        Statut statut = new Statut();
        statut.setNomStatut("Admin");

        assertEquals("Admin", statut.getNomStatut());
    }

    @Test
    void setNomStatut() {
        Statut statut = new Statut();

        statut.setNomStatut("Participant");

        assertEquals("Participant", statut.getNomStatut());
    }

    @Test
    void getUsers() {
        Statut statut = new Statut();
        List<User> users = List.of(new User(), new User());
        statut.setUsers(users);

        assertSame(users, statut.getUsers());
    }

    @Test
    void setUsers() {
        Statut statut = new Statut();
        List<User> users = List.of(new User());

        statut.setUsers(users);

        assertSame(users, statut.getUsers());
    }
}
