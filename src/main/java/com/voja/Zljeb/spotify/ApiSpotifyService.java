package com.voja.Zljeb.spotify;

import java.util.LinkedList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Track;

@Service
public class ApiSpotifyService implements SpotifyService{
    private String output; 
    private RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
    
    public String GetToken(Discography disc){  
    try{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", disc.clientId);
        body.add("client_secret", disc.clientSecret);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            disc.tokenApiUrl, 
            HttpMethod.POST,
            requestEntity,
            String.class);        
            System.out.println(responseEntity.getBody());
            return responseEntity.getBody().substring(17, 132);
        } catch(Exception e ){
            return "Error! "+e;
        }
    }
    public String GetAlbumsJson(Discography disc, String token){
        try{
        HttpHeaders response = new HttpHeaders();
        response.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            disc.albumApiUrl.replace("{id}", disc.zljebId), 
            HttpMethod.GET,
            entity,
            String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
        }catch(Exception e){
            return "Error: " +e;
        }
    }
    
    public List<Album> GetAlbums(String albumJson){
        List<Album> albums = new LinkedList<Album>();  
        try{        
            JsonNode jsonNode = objectMapper.readTree(albumJson);
            int total = jsonNode.path("total").asInt();          
            for(int i=0;i<total;i++){
                JsonNode album = jsonNode.path("items").get(i);    
                albums.add(new Album(
                    album.path("name").asText(),
                    album.path("id").asText(),
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
    public String GetTracksJson(Discography disc, String id, String token){       
        try{
        HttpHeaders response = new HttpHeaders();
        response.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            disc.tracksApiUrl.replace("{id}", id),
            HttpMethod.GET,
            entity,
            String.class);
        System.out.println(responseEntity.getBody());
        output = responseEntity.getBody();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return output;
    }
    public List<Track> GetTracks(String tracksJson){
        List<Track> tracks = new LinkedList<Track>();   
        try{                     
            JsonNode jsonNode = objectMapper.readTree(tracksJson);
            int total = jsonNode.path("total").asInt();  
                      
            for(int i=0;i<total;i++){
            JsonNode track = jsonNode.path("items").get(i);    
            tracks.add(new Track(
                track.path("id").asText(),
                track.path("name").asText(),
                track.path("external_urls").path("spotify").asText()
            ));
        System.out.println(tracks.get(i).getLink());
        
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return tracks;   
    }

}
