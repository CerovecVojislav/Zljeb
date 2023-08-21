package com.voja.Zljeb.Model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Touring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    List<String> TouringPlaces;


    public Touring(List<String> touringPlaces){
        this.TouringPlaces = touringPlaces;
    }
    public Touring(){
        
    }
    public List<String> getTouringPlaces() {
        return TouringPlaces;
    }
    public void setTouringPlaces(List<String> touringPlaces) {
        TouringPlaces = touringPlaces;
    }
}