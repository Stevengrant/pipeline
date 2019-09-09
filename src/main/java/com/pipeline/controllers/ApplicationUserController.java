package com.pipeline.controllers;

import com.pipeline.models.*;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ApplicationUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CandidateGroupRepository candidateGroupRepository;

    @Autowired
    ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    ProgressRepository progressRepository;
    //Modify here for /registration


    @PostMapping("/users")
    //Admin user posting
    public RedirectView createUser(String username, String password, String firstName, String lastName, Model m, Principal p) {
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName);
        applicationUserRepository.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/dashboard");
    }

//    Candidate User Posting
    @PostMapping("/users/{groupId}")
    public RedirectView createUser(@PathVariable long groupId, String username, String password, String firstName, String lastName, Model m, Principal p) {
        CandidateGroup cg = candidateGroupRepository.findById(groupId).get();
        List<ScheduledTask> tasks = scheduledTaskRepository.findByGroupThisTaskBelongsToId(groupId);
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,
                cg);
        applicationUserRepository.save(newUser);
        for (int i = 0; i < tasks.size(); i++){
            Date due =new Date(System.currentTimeMillis() + 241920000);
            Progress newProgress = new Progress(false,due, null,newUser, tasks.get(i));
            progressRepository.save(newProgress);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/dashboard");
    }
}

