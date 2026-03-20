package utcapitole.miage.tp3.tp5.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "THEMATIQUES")
public class Thematique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idThematique;

    private String nomThematique;

    @ManyToMany(mappedBy = "thematiques")
    private List<Conference> conferences;

    public Thematique() {
    }

    public Integer getIdThematique() {
        return idThematique;
    }

    public void setIdThematique(Integer idThematique) {
        this.idThematique = idThematique;
    }

    public String getNomThematique() {
        return nomThematique;
    }

    public void setNomThematique(String nomThematique) {
        this.nomThematique = nomThematique;
    }

    public List<Conference> getConferences() {
        return conferences;
    }

    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }
}
