package com.voja.Zljeb.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.voja.Zljeb.Interface.IAutentication;
import com.voja.Zljeb.Model.Autentication;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class AuthenticationController {
    public boolean authenticationBool = false;
    @Autowired
    private IAutentication autentication;

    @RequestMapping("/login")
    public String Login(@CookieValue(name = "login", defaultValue = "null")String cookie){
        if(cookie.equals("Zljeb")){
            authenticationBool = true;
            return "redirect:/dashboard";
        }
        return "login";
    }
    @PostMapping("/verify")
    public String Verify(@RequestParam("user") String user,
                        @RequestParam("password") String password,
                        @RequestParam(name = "remember",required = false) boolean remember,
                        HttpServletResponse response){
        if(UserChech(user, password)){  
            authenticationBool = true;
            if(remember){
                createCookie(response);
            }
            return "redirect:/dashboard";
        }
        else{
            return "redirect:/login";
        }
    }

    @RequestMapping("logout")
    public String Logout(HttpServletResponse response){
        Cookie oreo = new Cookie("login", null);
        oreo.setMaxAge(0);
        response.addCookie(oreo);
        authenticationBool = false;
        return "redirect:/";
    }
    public boolean UserChech(String user, String password){
        List<Autentication> List = autentication.findAll();
        for(Autentication admin : List){
            if(user.equals(admin.AdminUser) || password.equals(admin.Password)){
                return true;
            }
        }
        return false;
    }
    public void createCookie(HttpServletResponse response){
                Cookie oreo = new Cookie("login", "Zljeb");
                oreo.setMaxAge(3600);
                response.addCookie(oreo);
    }
}

