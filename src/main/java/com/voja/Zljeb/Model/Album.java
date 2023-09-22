package com.voja.Zljeb.Model;

public class Album {
    public String name;
    public String link;
    public String image;
    public String date;
    public String total_tracks;

    public Album(String name ,String link, String image, String date, int total){
        this.name = name;
        this.link = link;
        this.image = image;
        this.date = date;
        if (total<=3) {
            this.total_tracks = "Single";
        }
        else if (total<=6) {
            this.total_tracks = "EP";
        } else {
            this.total_tracks = "LP";
        }
    }
    public Album(){
        this.name = null;
        this.link = null;
        this.image = null;
        this.date = null;
    }
    public String getName(){
        return this.name;
    }
    public String getLink(){
        return this.link;
    }
    public String getImage(){
        return this.image;
    }
    public String getDate(){
        return this.date;
    }    
    private void setName(String name){
        this.name = name;
    }
    private void setLink(String link){
        this.link = link;
    }
    private void setImage(String image){
        this.image = image;
    }
    private void setDate(String date){
        this.date = date;
    }
}
