package com.voja.Zljeb.Interface;

import org.springframework.data.jpa.repository.JpaRepository;
import com.voja.Zljeb.Model.Touring;
import org.springframework.stereotype.Repository;

@Repository
public interface ITouring extends JpaRepository<Touring, Long>{
    
}