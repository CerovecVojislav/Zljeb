package com.voja.Zljeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;
import org.springframework.ui.Model;
import java.io.IOException;
import java.util.List;

// Controler for handling of dashboard.

@Controller
public class DashboardController {

    @Autowired
    private ITouring touring;
    @Autowired
    private IDiscography discography;
    @Autowired
    public AuthenticationController authenticationController;

    @GetMapping("/dashboard")
    public String Dashboard(Model model){
        if(!authenticationController.authenticationBool){
            return "redirect:/login";
        }
        List<Touring> tour = touring.findAll();
        List<Discography> disc = discography.findAll();
        model.addAttribute("disc", disc);
        model.addAttribute("tour", tour);
        return "dashboard";
    }
    @RequestMapping("/forms")
    public String Forms(Model model)throws IOException{

        return "forms";
    }

    @PostMapping("/createdisc") 
    public String CreateDisc(@RequestParam("clientId") String clientId,
                            @RequestParam("clientSecret") String clientSecret,
                            @RequestParam("zljebId") String zljebId,
                            @RequestParam("tokenApiUrl") String tokenApiUrl,
                            @RequestParam("albumApiUrl") String albumApiUrl,
                            @RequestParam("tracksApiUrl") String tracksApiUrl)
    {
        Discography disc = new Discography("client_credentials", clientId, clientSecret, zljebId, tokenApiUrl, albumApiUrl, tracksApiUrl);
        discography.save(disc);
        return "redirect:/dashboard";
    }
    @RequestMapping("/removedisc{id}")
    public String RemoveD(@PathVariable("id") long id){
        discography.deleteById(id);
        return "redirect:/dashboard";
    }
    @RequestMapping("/removetour{id}")
    public String RemoveT(@PathVariable("id") long id){
        touring.deleteById(id);
        return "redirect:/dashboard";
    }        
    @PostMapping("/createtour")
    public String CreateTour(@RequestParam("name") String name,
                            @RequestParam("location") String location,
                            @RequestParam("date") String date,
                            @RequestParam("link") String link){
        Touring tour = new Touring(name, location, date, link);
        touring.save(tour);                   
        return "redirect:/dashboard";
    }
}
