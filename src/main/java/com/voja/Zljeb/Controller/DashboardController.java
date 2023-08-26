package com.voja.Zljeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;
import org.springframework.ui.Model;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

// Controler for handling of dashboard.

@Controller
public class DashboardController {
@Autowired
private ITouring touring;
@Autowired
private IDiscography discography;

    @GetMapping("/dashboard")
    public String Dashboard(Model model){
        List<Touring> tour = touring.findAll();
        List<Discography> disc = discography.findAll();
        model.addAttribute("disc", disc);
        model.addAttribute("tour", tour);
        return "dashboard";
    }
    @RequestMapping("/forms")
    public String Forms(Model model){
        model.addAttribute("disc", new Discography());
        model.addAttribute("tour", new Touring());
        return "forms";
    }
    @RequestMapping("/login")
    public String Login(){
        return "login";
    }


    @Value("${upload.path}")
    private String uploadPath;

    
    @PostMapping("/createdisc")
    public String CreateDisc(@RequestParam("name") String Name,
                            @RequestParam("date") String Date,
                            @RequestParam("cover") MultipartFile coverFile){
        try{
            String fileName = coverFile.getOriginalFilename();
            byte[] cover = coverFile.getBytes();
            Discography disc = new Discography();
            disc.setName(Name);
            disc.setDate(Date);
            disc.setCover(fileName);
            Path path = Paths.get(uploadPath, fileName);
            Files.write(path, cover);
            discography.save(disc);
        }
        catch(Exception e ){
            
            return "forms";
        }
        
        return "redirect:/dashboard";
    }
}
