package com.voja.Zljeb.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voja.Zljeb.Interface.IDiscography;
import com.voja.Zljeb.Interface.ITouring;
import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Touring;
import com.voja.Zljeb.Model.Track;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.spotify.SpotifyService;

import org.springframework.ui.Model;



// (Navbar) Controler that will be used first when app is started.

@Controller
public class WelcomeController {
    @Autowired
    private IDiscography discography;
    @Autowired
    private ITouring touring;

    private SpotifyService ss;
    @Autowired
	public WelcomeController(SpotifyService ss) {
		this.ss = ss;
	}

    
    private String token;
    private String albumsJson;
    private String tracksJson;

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
    @GetMapping("/discography")
    public String Discography(Model model){
        
        List<Discography> disc = discography.findAll();
        token = ss.GetToken(disc.get(0));
        albumsJson = ss.GetAlbumsJson(disc.get(0), token);
        List<Album> album = ss.GetAlbums(albumsJson);
        model.addAttribute("album", album);
        return "discography";
    }
    @RequestMapping("/tours")
    public String Touring(Model model){
        List<Touring> data = touring.findAll();
        model.addAttribute("data", data);
        return "touring";
    }
    @RequestMapping("/tracks{id}")
    public String Tracks(@PathVariable("id") String id, Model model){
        List<Discography> disc = discography.findAll();
        tracksJson = ss.GetTracksJson(disc.get(0), id, token);    
        List<Track> track = ss.GetTracks(tracksJson);
        model.addAttribute("track", track);
        return "gallery";
    }
}
