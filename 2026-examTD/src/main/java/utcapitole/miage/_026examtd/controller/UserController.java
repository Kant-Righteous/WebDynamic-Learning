package utcapitole.miage._026examtd.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import utcapitole.miage._026examtd.model.User;
import utcapitole.miage._026examtd.service.UserService;

@Controller
public class UserController {

    private UserService userService;

    @GetMapping("/user")
    public String dashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            return "redirect:/login";
        }

        populateDashboard(model, user);
        return "user";
    }

    private void populateDashboard(Model model, User user){
        model.addAttribute("loginUser", user);
    }

    @GetMapping("/")
    public String user(HttpSession session) {
        return session.getAttribute("loginUser") == null ? "redirect:/login" : "redirect:/user";
    }

}
