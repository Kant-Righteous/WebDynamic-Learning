package utcapitole.miage.tp3.tp4.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conference")
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int idConf;

    private String titleConf;
    private Integer nbEditionConf;
    private String dtStartConf;
    private String dtEndConf;
    private String urlWebsiteConf;

    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @ManyToMany(mappedBy = "participatedConferences")
    private List<User> participants = new ArrayList<>();

    public Conference() {
    }

    public int getIdConf() {
        return idConf;
    }

    public void setIdConf(int idConf) {
        this.idConf = idConf;
    }

    public String getTitleConf() {
        return titleConf;
    }

    public void setTitleConf(String titleConf) {
        this.titleConf = titleConf;
    }

    public Integer getNbEditionConf() {
        return nbEditionConf;
    }

    public void setNbEditionConf(Integer nbEditionConf) {
        this.nbEditionConf = nbEditionConf;
    }

    public String getDtStartConf() {
        return dtStartConf;
    }

    public void setDtStartConf(String dtStartConf) {
        this.dtStartConf = dtStartConf;
    }

    public String getDtEndConf() {
        return dtEndConf;
    }

    public void setDtEndConf(String dtEndConf) {
        this.dtEndConf = dtEndConf;
    }

    public String getUrlWebsiteConf() {
        return urlWebsiteConf;
    }

    public void setUrlWebsiteConf(String urlWebsiteConf) {
        this.urlWebsiteConf = urlWebsiteConf;
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
}
