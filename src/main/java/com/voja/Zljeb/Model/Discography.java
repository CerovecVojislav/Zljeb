package com.voja.Zljeb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import java.util.Date;


@Entity
public class Discography {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String Name;
    public Date Date;
    @Lob
    public byte[] Cover;

    public Discography(String name, Date date, byte[] cover){
        this.Name = name;
        this.Date = date;
        this.Cover = cover;
    }
    public String getName(){
        return this.Name;
    }
    public Date getDate(){
        return this.Date;
    }
    public byte[] getCover(){
        return this.Cover;
    }
    public void setName(String name){
        this.Name = name;
    }
    public void setDate(Date date){
        this.Date = date;
    }
    public void setCover(byte[] cover){
        this.Cover = cover;
    }

}
