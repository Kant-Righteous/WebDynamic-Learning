package utcapitole.miage.tp3.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp3.tp4.model.Conference;
import utcapitole.miage.tp3.tp4.model.jpa.ConferenceService;
import utcapitole.miage.tp3.tp4.model.jpa.UserService;

@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;
    @Autowired
    private UserService userService;

    @GetMapping("/conferences")
    public String listConferences(Model model) {
        model.addAttribute("conferences", conferenceService.findAll());
        return "conferences";
    }

    @PostMapping("/addConference")
    public String addCOnference(Conference conference) {
        conferenceService.save(conference);
        return "redirect:/conferences";
    }

    @PostMapping("/delConference")
    public String delConference(@RequestParam int idConf) {
        conferenceService.deleteById(idConf);
        return "redirect:/conferences";
    }

    @GetMapping("/editConference")
    public String editConference(@RequestParam int idConf, Model model) {
        Conference conference = conferenceService.findById(idConf);
        model.addAttribute("conference", conference);
        model.addAttribute("users", userService.findAll());
        return "editConference";
    }

    @PostMapping("/updateConference")
    public String updateConference(Conference conference) {
        conferenceService.updateConferenceDetails(conference);
        return "redirect:/conferences";
    }

    @PostMapping("/conference/addParticipant")
    public String addParticipant(@RequestParam int idConf, @RequestParam int userId) {
        conferenceService.addParticipant(idConf, userId);
        return "redirect:/editConference?idConf=" + idConf;
    }

    @PostMapping("/conference/removeParticipant")
    public String removeParticipant(@RequestParam int idConf, @RequestParam int userId) {
        conferenceService.removeParticipant(idConf, userId);
        return "redirect:/editConference?idConf=" + idConf;
    }

    @PostMapping("/conference/setOrganizer")
    public String setOrganizer(@RequestParam int idConf, @RequestParam int userId) {
        conferenceService.setOrganizer(idConf, userId);
        return "redirect:/editConference?idConf=" + idConf;
    }

    @PostMapping("/conference/clearOrganizer")
    public String clearOrganizer(@RequestParam int idConf) {
        conferenceService.clearOrganizer(idConf);
        return "redirect:/editConference?idConf=" + idConf;
    }

}
