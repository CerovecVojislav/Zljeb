package com.voja.Zljeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.voja.Zljeb.Interface.IAutentication;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;

import org.springframework.ui.Model;
import java.util.List;

// Controler for handling of dashboard.

@Controller
public class DashboardController {
@Autowired
private ITouring touring;
@Autowired
private IDiscography discography;
@Autowired
private IAutentication autentications;

    @RequestMapping("/dashboard")
    public String Dashboard(Model model){
        List<Touring> tour = touring.findAll();
        List<Discography> disc = discography.findAll();
        model.addAttribute("disc", disc);
        model.addAttribute("tour", tour);
        return "dashboard";
    }
    @RequestMapping("/login")
    public String Login(){
        return "login";
    }
}
