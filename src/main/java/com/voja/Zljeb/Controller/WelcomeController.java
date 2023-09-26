package com.voja.Zljeb.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Touring;
import com.voja.Zljeb.Repository.IAlbums;
import com.voja.Zljeb.Repository.IDiscography;
import com.voja.Zljeb.Repository.ITouring;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.spotify.WebRequestHelper;
import com.voja.Zljeb.spotify.SpotifyService;

import org.springframework.ui.Model;



// (Navbar) Controler that will be used first when app is started.

@Controller
public class WelcomeController {
    @Autowired
    private IDiscography discography;


    @Autowired
    private ITouring touring;


    @Autowired
    private IAlbums albums;


    private SpotifyService ss;
    @Autowired
	public WelcomeController(SpotifyService ss) {
		this.ss = ss;
	}

    
    private String token;
    private String albumsJson;

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
        token = WebRequestHelper.GetToken(disc.get(0));
        albumsJson = WebRequestHelper.GetAlbumsJson(disc.get(0), token);
        List<Album> album = ss.GetAlbums(albumsJson);
        if(album.size()==0){
            album = albums.findAll();
        }
        else{
            albums.deleteAll();
            albums.saveAll(album);
        }
        if(album.size()%2!=0){
            albums.save(new Album());
            album = albums.findAll();
        }
        model.addAttribute("album", album);
        return "discography";
    }
    @RequestMapping("/tours")
    public String Touring(Model model){
        List<Touring> data = touring.findAll();
        model.addAttribute("data", data);
        return "touring";
    }
}
