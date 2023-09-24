package com.voja.Zljeb.spotify;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voja.Zljeb.Model.Album;

@Service
public class ApiSpotifyService implements SpotifyService{
    ObjectMapper objectMapper = new ObjectMapper();    
    public List<Album> GetAlbums(String albumJson){
        List<Album> albums = new LinkedList<Album>();  
        try{        
            JsonNode jsonNode = objectMapper.readTree(albumJson);
            int total = jsonNode.path("total").asInt();          
            for(int i=0;i<total;i++){
                JsonNode album = jsonNode.path("items").get(i);    
                albums.add(new Album(
                    album.path("name").asText(),
                    album.path("external_urls").path("spotify").asText(),
                    album.path("images").get(0).path("url").asText(),
                    album.path("release_date").asText(),
                    album.path("total_tracks").asInt()
                ));
            System.out.println(albums.get(i).getLink());
            }            
        } catch (Exception e) {
            e.printStackTrace();
        }
            return albums;
    }
}
