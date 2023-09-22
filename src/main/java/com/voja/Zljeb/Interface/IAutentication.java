package com.voja.Zljeb.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voja.Zljeb.Model.Autentication;

@Repository
public interface IAutentication extends JpaRepository<Autentication, Long> {
    
}
