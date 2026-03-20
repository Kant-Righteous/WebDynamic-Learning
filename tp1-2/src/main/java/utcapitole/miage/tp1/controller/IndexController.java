package utcapitole.miage.tp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utcapitole.miage.tp1.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/resa/")
public class IndexController {

    public static List<Reservation> reservationList = new ArrayList<>();

    @GetMapping({"", "/"})
    public String resa() {
        return "forward:/resa/index.html";
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
            @RequestParam(value = "conditions", defaultValue = "false") boolean con) {
        int nombrePersonnes = (nbP != null) ? nbP : 0;
        reservationList.add(new Reservation(nombrePersonnes, des, nom, mail, payment, iban, promo, con));
        return "redirect:/resa/";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Reservation> list() {
        return reservationList;
    }

}
