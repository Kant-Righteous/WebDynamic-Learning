package utcapitole.miage.tp3.tp5.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "STATUTS")
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codeStatut;

    private String nomStatut;

    @OneToMany(mappedBy = "statut")
    private List<User> users;

    public Statut() {
    }

    public Integer getCodeStatut() {
        return codeStatut;
    }

    public void setCodeStatut(Integer codeStatut) {
        this.codeStatut = codeStatut;
    }

    public String getNomStatut() {
        return nomStatut;
    }

    public void setNomStatut(String nomStatut) {
        this.nomStatut = nomStatut;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
