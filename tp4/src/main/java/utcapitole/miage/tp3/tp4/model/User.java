package utcapitole.miage.tp3.tp4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "organizer")
    private List<Conference> organizedConferences = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "conference_participants",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conference_id")
    )
    private List<Conference> participatedConferences = new ArrayList<>();

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

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
