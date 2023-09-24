package com.voja.Zljeb.spotify;

import java.util.List;

import com.voja.Zljeb.Model.Album;

public interface SpotifyService {
    
    //public String GetToken(Discography disc);

    //public String GetAlbumsJson(Discography disc, String token);

    public List<Album> GetAlbums(String ablumJson);

}
