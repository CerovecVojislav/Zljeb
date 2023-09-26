package com.voja.Zljeb.spotify;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.voja.Zljeb.Model.Discography;

public class WebRequestHelper {
    private static RestTemplate restTemplate = new RestTemplate();
    private static HttpHeaders headers = new HttpHeaders();
    
    public static String GetToken(Discography disc){  
    try{
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
    public static String GetAlbumsJson(Discography disc, String token){
        try{
        headers.setBearerAuth(token);
        HttpEntity<?> entity = new HttpEntity<>(headers);
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
}
