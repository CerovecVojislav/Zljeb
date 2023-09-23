package com.voja.Zljeb.spotify;

import java.util.List;

import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Discography;
import com.voja.Zljeb.Model.Track;

public interface SpotifyService {
    
    public String GetToken(Discography disc);

    public String GetAlbumsJson(Discography disc, String token);

    public List<Album> GetAlbums(String ablumJson);

    public String GetTracksJson(Discography disc, String id, String token);

    public List<Track> GetTracks(String id);
}
