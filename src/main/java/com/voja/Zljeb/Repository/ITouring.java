package com.voja.Zljeb.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.voja.Zljeb.Model.Touring;

@Repository
public interface ITouring extends JpaRepository<Touring, Long>{
    
}