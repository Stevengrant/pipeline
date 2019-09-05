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
        //Group if candidate
        //Groups associated with admin user
        //pull progress table
        //pull task table
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
    public String getSingleGroupView(@PathVariable long groupId, Model m){
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        m.addAttribute("group", group);
        return "groupView";
    }

    @GetMapping("/taskview/{groupId}")
    public String getTaskView(@PathVariable long groupId, Model m){
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        m.addAttribute("group", group);
        return "taskView";
    }

    @GetMapping("/taskview/edit/{taskId}")
    public String getTaskViewEdit(@PathVariable long taskId, Model m){
        ScheduledTask task = scheduledTaskRepository.findById(taskId).get();
        m.addAttribute("task", task);
        return "editTaskView";
    }

//    @GetMapping("/task/delete/{taskId}")
//    public String getTaskToDelete(@PathVariable long taskId, Model m){
//        ScheduledTask task = scheduledTaskRepository.findById(taskId).get();
//        m.addAttribute("task", task);
//        return "editTaskView";
//    }


    @GetMapping("/taskview")
    public String getTaskView(){
        return "taskView";
    }


    //Testing purpose front end


}
