package utcapitole.miage.tp3.tp5.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CONFERENCES")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idconf;

    private String titleconf;
    private Integer nbeditionconf;
    private String dtstartconf;
    private String dtendconf;
    private String urlwebsiteconf;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToMany(mappedBy = "participatedConferences")
    private List<User> participants;

    @ManyToMany
    @JoinTable(
            name = "TRAITER",
            joinColumns = @JoinColumn(name = "idConf"),
            inverseJoinColumns = @JoinColumn(name = "idThematique")
    )
    private List<Thematique> thematiques;

    public Conference() {
    }

    public Integer getIdconf() {
        return idconf;
    }

    public void setIdconf(Integer idconf) {
        this.idconf = idconf;
    }

    public String getTitleconf() {
        return titleconf;
    }

    public void setTitleconf(String titleconf) {
        this.titleconf = titleconf;
    }

    public Integer getNbeditionconf() {
        return nbeditionconf;
    }

    public void setNbeditionconf(Integer nbeditionconf) {
        this.nbeditionconf = nbeditionconf;
    }

    public String getDtstartconf() {
        return dtstartconf;
    }

    public void setDtstartconf(String dtstartconf) {
        this.dtstartconf = dtstartconf;
    }

    public String getDtendconf() {
        return dtendconf;
    }

    public void setDtendconf(String dtendconf) {
        this.dtendconf = dtendconf;
    }

    public String getUrlwebsiteconf() {
        return urlwebsiteconf;
    }

    public void setUrlwebsiteconf(String urlwebsiteconf) {
        this.urlwebsiteconf = urlwebsiteconf;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public List<Thematique> getThematiques() {
        return thematiques;
    }

    public void setThematiques(List<Thematique> thematiques) {
        this.thematiques = thematiques;
    }
}
