package com.voja.Zljeb.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.voja.Zljeb.Model.Discography;

@Repository
public interface IDiscography extends JpaRepository<Discography, Long>{
    
}
