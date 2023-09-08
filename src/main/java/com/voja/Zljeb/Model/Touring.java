package com.voja.Zljeb.Model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Touring {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    public String TouringName;
    public String Location;
    public String Date;
    public String InstaLink;

    public Touring(String touringName, String location, String date, String link){
        this.TouringName = touringName;
        this.Location = location;
        this.Date = date;
        this.InstaLink = link;
    }
    public Touring(){
        this.TouringName = "null";
        this.Location ="null";
    }
    public String getDate() {
        return Date;
    }
    public String getLocation() {
        return Location;
    }
    public String getTouringName() {
        return TouringName;
    }
    public String getInstaLink() {
        return InstaLink;
    }
    public void setDate(String date) {
        Date = date;
    }
    public void setLocation(String location) {
        Location = location;
    }
    public void setTouringPlace(String touringName) {
        TouringName = touringName;
    }
    public void setInstaLink(String instaLink) {
        InstaLink = instaLink;
    }
}