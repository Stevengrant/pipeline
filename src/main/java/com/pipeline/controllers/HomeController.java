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

    @GetMapping("/logout")
    public String getLogout(){
        return "root";
    }


//Testing purpose front end
    @GetMapping("/groupview")
    public String getGroupView(){

        return "groupView";
    }


    @GetMapping("/groupview/{groupId}")
    public String getSingleGroupView(@PathVariable long groupId, Principal p, Model m){
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        m.addAttribute("group", group);
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "groupView";
    }

    @GetMapping("/taskview")
    public String getTaskView(){
        return "taskView";
    }

    @GetMapping("/aboutUs")
    public String getAboutUs() {
        return "aboutUs";
    }


    //Testing purpose front end


}
