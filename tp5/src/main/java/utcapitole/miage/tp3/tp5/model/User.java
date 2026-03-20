package utcapitole.miage.tp3.tp5.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @ManyToOne
    @JoinColumn(name = "codeStatut")
    private Statut statut;

    @OneToMany(mappedBy = "organizer")
    private List<Conference> organizedConferences;

    @ManyToMany
    @JoinTable(
            name = "user_participate_conference",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private List<Conference> participatedConferences;

    public User() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Conference> getOrganizedConferences() {
        return organizedConferences;
    }

    public void setOrganizedConferences(List<Conference> organizedConferences) {
        this.organizedConferences = organizedConferences;
    }

    public List<Conference> getParticipatedConferences() {
        return participatedConferences;
    }

    public void setParticipatedConferences(List<Conference> participatedConferences) {
        this.participatedConferences = participatedConferences;
    }
}
