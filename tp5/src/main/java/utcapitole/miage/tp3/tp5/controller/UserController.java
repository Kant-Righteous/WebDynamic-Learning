package utcapitole.miage.tp3.tp5.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp3.tp5.model.Statut;
import utcapitole.miage.tp3.tp5.model.User;
import utcapitole.miage.tp3.tp5.model.jpa.Statut.StatutService;
import utcapitole.miage.tp3.tp5.model.jpa.User.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StatutService statutService;

    @GetMapping("/")
    public String home(HttpSession session) {
        return session.getAttribute("loggedInUser") == null ? "redirect:/login" : "redirect:/conferences";
    }

    @GetMapping("/createUser.html")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("statuts", statutService.findAll());
        return "createUser";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/addUser")
    public String addUser(
            @ModelAttribute User user,
            @RequestParam(value = "statutId", required = false) Integer statutId,
            Model model) {
        try {
            if (statutId != null) {
                Statut statut = statutService.findById(statutId);
                user.setStatut(statut);
            }
            userService.saveUser(user, user.getPassword());
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("user", user);
            model.addAttribute("statuts", statutService.findAll());
            model.addAttribute("errorMessage", e.getMessage());
            return "createUser";
        }
    }

    @PostMapping("/login")
    public String login(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            HttpSession session,
            Model model) {
        User user = userService.verifyLogin(email, password);
        if (user == null) {
            model.addAttribute("errorMessage", "Email ou mot de passe invalide.");
            return "login";
        }

        session.setAttribute("loggedInUser", user);
        return "redirect:/conferences";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
