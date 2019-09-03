package com.pipeline.root.controllers;

import com.chriscoulon.codefellowship.models.ApplicationUser;
import com.chriscoulon.codefellowship.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.HashSet;
//import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @PostMapping("/users")
    public RedirectView createUser(String username, String password, String firstName, String lastName,
                                   Date dateOfBirth, String bio, Model m, Principal p) {
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName, dateOfBirth, bio);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        m.addAttribute("user", p);
        m.addAttribute("notPrincipalUser", newUser);
        return new RedirectView("/userProfile");
    }

    @GetMapping("/userProfile")
    public String pullUserProfile(Model m, Principal p) {
        ApplicationUser currUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("notPrincipalUser", currUser);
        m.addAttribute("user", p);
        return "userProfile";
    }

    @PostMapping ("/applicationUser/follow/{idToFollow}")
    public RedirectView followApplicationUser(@PathVariable long idToFollow, Model m, Principal p) {
       ApplicationUser applicationUserToFollow = applicationUserRepository.findById(idToFollow).get();
       applicationUserToFollow.setUsersFollowingMe(applicationUserRepository.findByUsername(p.getName()));
       applicationUserRepository.save(applicationUserToFollow);
       return new RedirectView("/viewAllUsers");
    }




}

