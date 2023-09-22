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
import com.voja.Zljeb.Model.Track;

@Service
public class ApiSpotifyService implements SpotifyService{
    public String token;
    private String tracksJson;
    private String albumJson;
    private RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();

    public String GetToken(){
    HttpHeaders headers = new HttpHeaders();
    try{
        String spotifyApiEndpoint = "https://accounts.spotify.com/api/token"; 
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "client_credentials");
        body.add("client_id", "5afc7d66a16542c892d73b20fd299686");
        body.add("client_secret", "70efbc3d05324d60bf33349418465a20");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            spotifyApiEndpoint, 
            HttpMethod.POST,
            requestEntity,
            String.class);        
            System.out.println(responseEntity.getBody());
            token = responseEntity.getBody().substring(17, 132);
            System.out.println(token);;
            return token;  
        } catch(Exception e ){
            return "Error! "+e;
        }
    }
    public String GetAlbumsJson(){
        try{
        String zljebID="7kMmPqOxMLkqi0HTAtpyGb";
        String albumUrl = "https://api.spotify.com/v1/artists/"+zljebID+"/albums";
        HttpHeaders response = new HttpHeaders();
        response.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            albumUrl, 
            HttpMethod.GET,
            entity,
            String.class);
        albumJson = responseEntity.getBody();
        System.out.println(responseEntity.getBody());
        }catch(Exception e){
            return "Error: " +e;
        }
        return albumJson;
    }
    
    public List<Album> GetAlbums(){
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
    public String GetTracksJson(String id){        
        try{
        String tracksUrl = "https://api.spotify.com/v1/albums/"+id+"/tracks";
        HttpHeaders response = new HttpHeaders();
        response.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(response);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
            tracksUrl, 
            HttpMethod.GET,
            entity,
            String.class);
        System.out.println(responseEntity.getBody());
        tracksJson = responseEntity.getBody();
        return responseEntity.getBody();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return tracksJson;
    }
    public List<Track> GetTracks(String id){
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
