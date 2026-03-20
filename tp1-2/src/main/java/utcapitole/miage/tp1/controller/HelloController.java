package utcapitole.miage.tp1.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

//    @GetMapping("/world")
//    public String helloWorld(@RequestParam(value = "name",defaultValue = "World") String name){
//        return "Hello " + name;
//    }

    @GetMapping("/person/{name}")
    public String helloSomeone(@PathVariable String name){
        return "Hello " + name;
    }

}
