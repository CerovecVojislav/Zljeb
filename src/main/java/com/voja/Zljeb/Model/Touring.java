package com.voja.Zljeb.Model;

import java.util.Date;
import java.util.ArrayList;
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
    
    String TouringPlace;
    String Location;
    Date Date;

    public Touring(String touringPlaces, String location, Date date){
        this.TouringPlace = touringPlaces;
        this.Location = location;
        this.Date = date;
    }
    public Touring(){
        this.TouringPlace = "null";
        this.Location ="null";
    }
    public Date getDate() {
        return Date;
    }
    public String getLocation() {
        return Location;
    }
    public String getTouringPlace() {
        return TouringPlace;
    }
    public void setDate(Date date) {
        Date = date;
    }
    public void setLocation(String location) {
        Location = location;
    }
    public void setTouringPlace(String touringPlace) {
        TouringPlace = touringPlace;
    }
}