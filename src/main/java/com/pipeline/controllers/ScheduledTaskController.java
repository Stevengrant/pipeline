package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class ScheduledTaskController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    CandidateGroupRepository candidateGroupRepository;
    @Autowired
    ScheduledTaskRepository scheduledTaskRepository;
    @PostMapping("/addtask/{groupId}")
    public RedirectView addTask(@PathVariable long groupId, Principal p, String nameOfTask, String instructions, String poc ){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        //This might need some work
        //Checking to see if the person who's posting the new task is in fact the
        //owner of the group.
        if(group.getOwner() != user){
            return new RedirectView("fuckoff");
        }
        ScheduledTask task = new ScheduledTask(nameOfTask,instructions,poc);
        scheduledTaskRepository.save(task);
        return new RedirectView("/dashboard");
    }

}
