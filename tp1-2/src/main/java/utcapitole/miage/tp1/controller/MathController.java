package utcapitole.miage.tp1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/math")
public class MathController {

    @GetMapping("/bound")
    public String mathBound(){
        return "forward:/bound.html";
    }

    @GetMapping("/table")
    @ResponseBody
    public String mathTable(@RequestParam(value = "min")int min, @RequestParam(value = "max")int max){
        StringBuilder html = new StringBuilder();

        html.append("<table border='1'>");
        html.append("<tr>");
        html.append("<th>Nombre</th>");
        html.append("<th>Carré</th>");

        for (int i = min; i <= max; i++) {
            html.append("<tr>");
            html.append("<th>").append(i).append("</th>");
            html.append("<th>").append(i * i).append("</th>");
            html.append("</tr>");
        }

        html.append("</table>");

        return html.toString();
    }

}
