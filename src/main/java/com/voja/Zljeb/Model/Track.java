package com.voja.Zljeb.Model;

public class Track {
    public String id;
    public String name;
    public String link;
    public Track(String id, String name, String link){
        this.id=id;
        this.name=name;
        this.link=link;
    }
    public Track(){
        this.id=null;
        this.name=null;
        this.link=null;
    }
    public String getName(){
        return this.name;
    }
    public String getLink(){
        return this.link;
    }
    private void setName(String name){
        this.name = name;
    }
    private void setLink(String link){
        this.link = link;
    }
}
