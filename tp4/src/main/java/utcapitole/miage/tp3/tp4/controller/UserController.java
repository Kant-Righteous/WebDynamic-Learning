package utcapitole.miage.tp3.tp4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp3.tp4.model.User;
import utcapitole.miage.tp3.tp4.model.jpa.UserService;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addUser")
    public String addUser(User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/users/participations")
    public String userParticipations(@RequestParam(required = false) Integer id, Model model) {
        model.addAttribute("selectedId", id);
        if (id == null) {
            return "userParticipations";
        }

        User user = userService.findWithParticipatedConferencesById(id);
        model.addAttribute("user", user);
        model.addAttribute("notFound", user == null);
        return "userParticipations";
    }
}
