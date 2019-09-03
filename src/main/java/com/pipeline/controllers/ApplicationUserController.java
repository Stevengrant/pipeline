package com.pipeline.controllers;

import com.pipeline.models.ApplicationUser;
import com.pipeline.models.ApplicationUserRepository;
import com.pipeline.models.CandidateGroup;
import com.pipeline.models.CandidateGroupRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.HashSet;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CandidateGroupRepository candidateGroupRepository;

    @PostMapping("/users")
    //Admin user posting
    public RedirectView createUser(String username, String password, String firstName, String lastName, Model m, Principal p) {
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        m.addAttribute("loggedInUser", newUser);
        return new RedirectView("/dashboard");
    }


//    Candidate User Posting
    @PostMapping("/users/{groupId}")
    public RedirectView createUser(@PathVariable long groupId, String username, String password, String firstName, String lastName, Model m, Principal p) {

        CandidateGroup cg = candidateGroupRepository.findById(groupId).get();

        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,
                cg);
        applicationUserRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        m.addAttribute("loggedInUser", newUser);
        return new RedirectView("/dashboard");
    }

    @GetMapping("/dashboard")
    public String pullDashboard(Principal p, Model m){
        ApplicationUser u = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedInUser", u);
        return "dashboard";
    }


}

