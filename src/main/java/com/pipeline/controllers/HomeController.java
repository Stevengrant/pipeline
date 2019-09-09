package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CandidateGroupRepository candidateGroupRepository;

    @Autowired
    ScheduledTaskRepository scheduledTaskRepository;

    @Autowired
    ProgressRepository progressRepository;

    @GetMapping("/")
    public String getRoot(){
        return "root";
    }

    @GetMapping("/login")
    public RedirectView getLogin(){
        return new RedirectView("/");
    }

    @GetMapping("/registration/{id}")
    public String getRegistration(@PathVariable long id, Model m){
        m.addAttribute("isCandidate",true);
        m.addAttribute("groupId", id);
        return "registration";
    }

    @GetMapping("/registration")
    public String getRegistration(Model m){
        m.addAttribute("isCandidate",false);
        return "registration";
    }

    @GetMapping("/dashboard")
    public String getDash(Principal p, Model m){

        ApplicationUser currentUser = applicationUserRepository.findByUsername(p.getName());

        m.addAttribute("loggedInUser", currentUser);
        return "dashboard";
    }

    // The /logout mapping is taken care of by the WebSecurityConfig; this shouldn't be here at all.

    // The fact that between the CandidateGroupController and the HomeController,
    // there are capitalized and non-capitalized versions of the group[vV]iew routes, makes
    // me VERY nervous for the development process here.

    // And the taskview route here isn't linked from anywhere, and will also
    // always result in an error page... I'm extremely confused about why a lot
    // of this code exists!

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }

}
