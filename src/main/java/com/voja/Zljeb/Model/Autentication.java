package com.voja.Zljeb.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Autentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String AdminUser;
    public String Email;
    public String Password;
    public Autentication(String user, String password, String email){
        this.AdminUser = user;
        this.Password = password;
        this.Email = email;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public String getUser() {
        return AdminUser;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setUser(String user) {
        AdminUser = user;
    }
}
    
