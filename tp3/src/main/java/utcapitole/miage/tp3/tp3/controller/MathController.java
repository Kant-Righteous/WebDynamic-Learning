package utcapitole.miage.tp3.tp3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/math")
public class MathController {

    @GetMapping({ "", "/" })
    public String math() {
        return "math";
    }

    @GetMapping("/result")
    public String result(@RequestParam int min, @RequestParam int max, Model model) {
        int[] numberList = new int[max - min + 1];
        for (int i = min; i <= max; i++) {
            numberList[i - min] = i;
        }
        model.addAttribute("numberList", numberList);
        return "result";
    }

}
