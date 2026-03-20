package utcapitole.miage.tp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class FormController {

    @GetMapping("/form")
    public String showForm() {
        return "forward:/myform.html";
    }

    @GetMapping("/world")
    @ResponseBody
    public String handleForm(@RequestParam(value = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }

}
