package utcapitole.miage.tp6_7.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp6_7.entity.User;
import utcapitole.miage.tp6_7.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String password, Model model) {
        try{
            userService.registerUser(username, password);
            return "redirect:/login";
        } catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model, HttpSession session) {
        try{
            User loginUser = userService.loginUser(username, password);
            session.setAttribute("loginUser", loginUser);
            return "redirect:/films";
        } catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
