package com.pipeline.controllers;

import com.pipeline.models.ApplicationUser;
import com.pipeline.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @GetMapping("/")
    public String getRoot(){
        return "root";
    }

    @GetMapping("/registration")
    public String getRegistration(){
        return "registration";
    }
    @GetMapping("/dashboard")
    public String getDash(Principal p, Model m){
        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedInUser", currentUser);
        return "dashboard";
    }

//    @PostMapping("/login")
//    public RedirectView postLog (String username){
//
//        applicationUserRepository.findByUsername()
//        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        m.addAttribute("loggedInUser", newUser);
//        return new RedirectView("/dashboard");
//    }
//    @GetMapping("/login")
//    public String getLogin (){
//        return "login";
//    }
    @GetMapping("/logout")
    public String getLogout(){
        return "root";
    };

}
