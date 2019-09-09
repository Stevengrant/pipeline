package com.pipeline.controllers;

// Lots of unused (and in some cases, warning-giving) imports!
import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;

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
    // Unused parameters make me nervous... why are they here?
    @PostMapping("/users/{groupId}")
    public RedirectView createUser(@PathVariable long groupId, String username, String password, String firstName, String lastName) {
        CandidateGroup cg = candidateGroupRepository.findById(groupId).get();
        List<ScheduledTask> tasks = scheduledTaskRepository.findByGroupThisTaskBelongsToId(groupId);
        ApplicationUser newUser = new ApplicationUser(username, encoder.encode(password), firstName, lastName,
                cg);
        applicationUserRepository.save(newUser);
        for (int i = 0; i < tasks.size(); i++){
            // Unclear what this magic number means! Needs a comment and/or to be written as a multiplication.
            Date due =new Date(System.currentTimeMillis() + 241920000);
            Progress newProgress = new Progress(false,due, null,newUser, tasks.get(i));
            progressRepository.save(newProgress);
        }
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new HashSet<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/dashboard");
    }
}

