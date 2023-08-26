package com.voja.Zljeb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Discography {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String Name;
    public String Date;
    public String Cover;
    public Discography(){

    }
    public Discography(String name, String date, String cover){
        this.Name = name;
        this.Date = date;
        this.Cover = cover;
    }
    public String getName(){
        return this.Name;
    }
    public String getDate(){
        return this.Date;
    }
    public String getCover(){
        return this.Cover;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setDate(String date){
        this.Date = date;
    }
    public void setCover(String cover){
        this.Cover = cover;
    }

}
