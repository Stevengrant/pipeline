package com.pipeline.controllers;

import com.pipeline.models.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
//import java.util.ArrayList;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

//    @PostMapping("/users")
//    public RedirectView createUser(String username, String password, String firstName, String lastName,
//                                   Date dateOfBirth, String bio, Model m, Principal p) {
//        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,
//                scheduledEvents);
//        applicationUserRepository.save(newUser);
//        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        m.addAttribute("user", p);
//        m.addAttribute("notPrincipalUser", newUser);
//        return new RedirectView("/userProfile");
//    }








}

