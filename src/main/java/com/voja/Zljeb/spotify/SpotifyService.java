package com.voja.Zljeb.spotify;

import java.util.List;

import com.voja.Zljeb.Model.Album;
import com.voja.Zljeb.Model.Track;

public interface SpotifyService {
    
    public String GetToken();

    public String GetAlbumsJson();

    public List<Album> GetAlbums();

    public String GetTracksJson(String id);

    public List<Track> GetTracks(String id);
}
