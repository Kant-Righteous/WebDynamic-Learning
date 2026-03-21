package utcapitole.miage.tp3.tp5.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp3.tp5.model.Conference;
import utcapitole.miage.tp3.tp5.model.User;
import utcapitole.miage.tp3.tp5.model.jpa.Conference.ConferenceService;
import utcapitole.miage.tp3.tp5.model.jpa.Thematique.ThematiqueService;

import java.util.List;

@Controller
public class ConferenceController {

    @Autowired
    private ConferenceService conferenceService;

    @Autowired
    private ThematiqueService thematiqueService;

    @GetMapping("/conferences")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        populateDashboard(model, user);
        return "conferences";
    }

    @PostMapping("/conferences/create")
    public String createConference(
            @ModelAttribute Conference conference,
            @RequestParam(value = "thematiqueIds", required = false) List<Integer> thematiqueIds,
            HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        conferenceService.createConference(conference, thematiqueIds, user);
        return "redirect:/conferences";
    }

    @PostMapping("/conferences/join")
    public String joinConference(
            @RequestParam("conferenceId") Integer conferenceId,
            HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            return "redirect:/login";
        }

        conferenceService.joinConference(conferenceId, user);
        return "redirect:/conferences";
    }

    private void populateDashboard(Model model, User user) {
        model.addAttribute("loggedInUser", user);
        model.addAttribute("conferenceForm", new Conference());
        model.addAttribute("thematiques", thematiqueService.findAll());
        model.addAttribute("allConferences", conferenceService.findAll());
        model.addAttribute("joinableConferenceIds", conferenceService.getJoinableConferenceIds(user));
        model.addAttribute("organizedConferences", conferenceService.getConferencesOrganizedBy(user));
        model.addAttribute("participatingConferences", conferenceService.getConferencesParticipatingIn(user));
    }
}
