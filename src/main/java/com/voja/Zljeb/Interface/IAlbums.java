package com.voja.Zljeb.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voja.Zljeb.Model.Album;

@Repository
public interface IAlbums extends JpaRepository<Album, Long> {
    
}
