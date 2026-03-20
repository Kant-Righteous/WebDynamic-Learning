package utcapitole.miage.tp3.tp3.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import utcapitole.miage.tp3.tp3.model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/resa")
public class IndexController {

    Map<String, List<Reservation>> database = new HashMap<>();

    @GetMapping({ "", "/" })
    public String resa(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);
        return "index";
    }

    @GetMapping("/save")
    public String save(
            @RequestParam(value = "nombre", required = false) Integer nbP,
            @RequestParam(value = "destination", required = false) String des,
            @RequestParam(value = "nom", required = false) String nom,
            @RequestParam(value = "email", required = false) String mail,
            @RequestParam(value = "paiement", required = false) String payment,
            @RequestParam(value = "iban", required = false) String iban,
            @RequestParam(value = "promo", defaultValue = "false") boolean promo,
            @RequestParam(value = "conditions", defaultValue = "false") boolean con,
            Model model,
            HttpSession session) {
        int nombrePersonnes = (nbP != null) ? nbP : 0;
        if (nombrePersonnes > 10) {
            nombrePersonnes = 10;
        }
        model.addAttribute("nbP", nombrePersonnes)
                .addAttribute("des", des)
                .addAttribute("nom", nom)
                .addAttribute("mail", mail)
                .addAttribute("payment", payment)
                .addAttribute("iban", iban)
                .addAttribute("promo", promo)
                .addAttribute("con", con);

        String username = (String) session.getAttribute("username");

        if (username != null) {
            List<Reservation> userReservations = database.get(username);

            if (userReservations == null) {
                userReservations = new ArrayList<>();
                database.put(username, userReservations);
            }

            userReservations.add(new Reservation(nombrePersonnes, des, nom, mail, payment, iban, promo, con));
        }

        return "confirmation";
    }

    @GetMapping("/login")
    public String login(@RequestParam("username") String username, HttpSession session) {
        session.setAttribute("username", username);
        return "redirect:/resa";
    }

    @GetMapping("/list")
    public String getReservationList(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        model.addAttribute("username", username);

        List<Reservation> userReservations = new ArrayList<>();
        if (username != null) {
            List<Reservation> dbReservations = database.get(username);
            if (dbReservations != null) {
                userReservations = dbReservations;
            }
        }

        model.addAttribute("reservationList", userReservations);

        return "reservationList";
    }

    @GetMapping("/cancel")
    public String cancelReservation(HttpSession session) {
        String username = (String) session.getAttribute("username");

        if (username != null) {
            List<Reservation> userReservations = database.get(username);

            if (userReservations != null && !userReservations.isEmpty()) {
                userReservations.remove(userReservations.size() - 1);
            }
        }
        return "redirect:/resa";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/resa";
    }

}
