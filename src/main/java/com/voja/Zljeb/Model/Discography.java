package com.voja.Zljeb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Discography {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String grantType;
    public String clientId;
    public String clientSecret;

    public String zljebId;
    public String tokenApiUrl;
    public String albumApiUrl;
    public String tracksApiUrl;

    public Discography(String grantType, String clientId, String clientSecret, String zljebId, String tokenApiUrl, String albumApiUrl, String tracksApiUrl){
        this.grantType = grantType;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.zljebId = zljebId;
        this.tokenApiUrl = tokenApiUrl;
        this.albumApiUrl = albumApiUrl;
        this.tracksApiUrl = tracksApiUrl;
    }
    public Discography(){
        this.grantType = null;
        this.clientId = null;
        this.clientSecret = null;
        this.zljebId = null;
        this.tokenApiUrl = null;
        this.albumApiUrl = null;
        this.tracksApiUrl = null;
    }
    public String getAlbumApiUrl() {
        return albumApiUrl;
    }
    public String getClientId() {
        return clientId;
    }
    public String getClientSecret() {
        return clientSecret;
    }
    public String getGrantType() {
        return grantType;
    }
    public Long getId() {
        return id;
    }
    public String getTokenApiUrl() {
        return tokenApiUrl;
    }
    public String getTracksApiUrl() {
        return tracksApiUrl;
    }
    public String getZljebId() {
        return zljebId;
    }
    private void setAlbumApiUrl(String albumApiUrl) {
        this.albumApiUrl = albumApiUrl;
    }
    private void setClientId(String clientId) {
        this.clientId = clientId;
    }
    private void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    private void setGrantType(String grantType) {
        this.grantType = grantType;
    }
    private void setTokenApiUrl(String tokenApiUrl) {
        this.tokenApiUrl = tokenApiUrl;
    }
    private void setTracksApiUrl(String tracksApiUrl) {
        this.tracksApiUrl = tracksApiUrl;
    }
    private void setZljebId(String zljebId) {
        this.zljebId = zljebId;
    }
}
