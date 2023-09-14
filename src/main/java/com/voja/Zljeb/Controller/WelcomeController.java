package com.voja.Zljeb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;


// (Navbar) Controler that will be used first when app is started.

@Controller
public class WelcomeController {
    @Autowired
    private IDiscography discograhpy;
    @Autowired
    private ITouring touring;


    @RequestMapping("/")
    public String Index(Model model){
        List<Touring> data = touring.findAll();
        model.addAttribute("data", data);
        return "index";
    }
    @RequestMapping("/contact")
    public String Contact(){
        return "contact";
    }
    @RequestMapping("/discography")
    public String Discography(Model model){
        List<Discography> data = discograhpy.findAll();
        model.addAttribute("data", data);
        return "discography";
    }
    @RequestMapping("/tours")
    public String Touring(Model model){
        List<Touring> data = touring.findAll();
        model.addAttribute("data", data);
        return "touring";
    }
}
