package com.voja.Zljeb.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Touring;
import com.voja.Zljeb.Storage.StorageService;
import com.voja.Zljeb.spotify.ApiSpotifyService;
import com.voja.Zljeb.spotify.SpotifyService;

import org.springframework.ui.Model;



// (Navbar) Controler that will be used first when app is started.

@Controller
public class WelcomeController {
    @Autowired
    private IDiscography discograhpy;
    @Autowired
    private ITouring touring;

    private SpotifyService ss;
    @Autowired
	public WelcomeController(SpotifyService ss) {
		this.ss = ss;
	}

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
        ss.GetToken();
        ss.GetAlbumsJson();
        List<Album> album = ss.GetAlbums();
        List<Discography> data = discograhpy.findAll();
        model.addAttribute("album", album);
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
