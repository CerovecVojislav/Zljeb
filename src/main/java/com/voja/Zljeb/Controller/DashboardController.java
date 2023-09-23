package com.voja.Zljeb.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;
import com.voja.Zljeb.Storage.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

// Controler for handling of dashboard.

@Controller
public class DashboardController {

    private final StorageService storageService;
    @Autowired
	public DashboardController(StorageService storageService) {
		this.storageService = storageService;
	}
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
        if(!authenticationController.authenticationBool){
            return "redirect:/login";
        }
        		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(DashboardController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));
        return "forms";
    }
    @RequestMapping("/files/{filename:.+}")
    @ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
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
