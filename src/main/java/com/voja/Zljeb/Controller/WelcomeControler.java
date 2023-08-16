package com.voja.Zljeb.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// (Navbar) Controler that will be used first when app is started.

@Controller
public class WelcomeControler {
    @RequestMapping("/")
    public String Index(){
        return "index";
    }
    @RequestMapping("/contact")
    public String Contact(){
        return "contact";
    }
    @RequestMapping("/discography")
    public String Discography(){
        return "contact";
    }
}
