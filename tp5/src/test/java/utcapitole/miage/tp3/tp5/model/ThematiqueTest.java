package utcapitole.miage.tp3.tp5.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ThematiqueTest {

    @Test
    void constructor() {
        assertDoesNotThrow(() -> new Thematique());
    }

    @Test
    void getIdThematique() {
        Thematique thematique = new Thematique();
        thematique.setIdThematique(1);

        assertEquals(1, thematique.getIdThematique());
    }

    @Test
    void setIdThematique() {
        Thematique thematique = new Thematique();

        thematique.setIdThematique(2);

        assertEquals(2, thematique.getIdThematique());
    }

    @Test
    void getNomThematique() {
        Thematique thematique = new Thematique();
        thematique.setNomThematique("IA");

        assertEquals("IA", thematique.getNomThematique());
    }

    @Test
    void setNomThematique() {
        Thematique thematique = new Thematique();

        thematique.setNomThematique("Cloud");

        assertEquals("Cloud", thematique.getNomThematique());
    }

    @Test
    void getConferences() {
        Thematique thematique = new Thematique();
        List<Conference> conferences = List.of(new Conference(), new Conference());
        thematique.setConferences(conferences);

        assertSame(conferences, thematique.getConferences());
    }

    @Test
    void setConferences() {
        Thematique thematique = new Thematique();
        List<Conference> conferences = List.of(new Conference());

        thematique.setConferences(conferences);

        assertSame(conferences, thematique.getConferences());
    }
}
