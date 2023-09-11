package com.voja.Zljeb.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.voja.Zljeb.Interface.IAutentication;
import com.voja.Zljeb.Model.Autentication;


@Controller
public class AuthenticationController {
    public boolean authenticationBool = false;
    @Autowired
    private IAutentication autentication;

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }
    public boolean UserChech(String user, String password){
        List<Autentication> List = autentication.findAll();
        for(Autentication admin : List){
            if(admin.AdminUser == user || admin.Password == password){
                return true;
            }
        }
        return false;
    }
    @PostMapping("/verify")
    public String Verify(@RequestParam("user") String user,
                        @RequestParam("password") String password){
        if(UserChech(user, password)){  
        authenticationBool = true;
        return "redirect:/dashboard";
        }
        else{
        return "redirect:/login";
        }
    }

    @RequestMapping("logout")
    public String Logout(){
        authenticationBool = false;
        return "redirect:/";
    }
}

