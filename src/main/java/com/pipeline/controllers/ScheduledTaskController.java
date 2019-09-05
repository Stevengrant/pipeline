package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ScheduledTaskController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;
    @Autowired
    CandidateGroupRepository candidateGroupRepository;
    @Autowired
    ScheduledTaskRepository scheduledTaskRepository;

    //create
    @PostMapping("/addtask/{groupId}")
    public RedirectView addTask(@PathVariable long groupId, Principal p, String nameOfTask, String instructions, String poc, String taskLink ){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        //TODO: This might need some work
        //Checking to see if the person who's posting the new task is in fact the
        //owner of the group.
        if(!group.getOwner().equals(user)){
            return new RedirectView("fuckoff");
        }
//        List instructionList = new List<String>();
//        instructionList.add(instructions);
        ScheduledTask task = new ScheduledTask(nameOfTask,instructions, poc, taskLink, group );
        group.setScheduledTasks(task);
        scheduledTaskRepository.save(task);
        return new RedirectView("/dashboard");
    }

    //update
    @PostMapping("/task/update/{id}")
    public RedirectView updateTask(@PathVariable long id, Principal p, String nameOfTask, String instructions, String poc) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        ScheduledTask taskToBeUpdated = scheduledTaskRepository.findById(id).get();
        if (!taskToBeUpdated.getGroupThisTaskBelongsTo().getOwner().equals(user)) {
            return new RedirectView("/fuckoff");
        }
        if(nameOfTask != null){
            taskToBeUpdated.setName(nameOfTask);
        }
        if(instructions != null){
            taskToBeUpdated.setInstructions(instructions);
        }
        if(poc != null){
            taskToBeUpdated.setPointOfContact(poc);
        }
//        taskToBeUpdated.setPointOfContact(poc);
        scheduledTaskRepository.save(taskToBeUpdated);
        return new RedirectView("/dashboard");

    }

    @GetMapping("/taskview/{groupId}")
    public String getTaskView(@PathVariable long groupId, Model m){
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        m.addAttribute("group", group);
        return "taskView";
    }

    @GetMapping("/taskview/edit/{taskId}")
    public String getTaskViewEdit(@PathVariable long taskId, Model m, Principal p){
        ScheduledTask task = scheduledTaskRepository.findById(taskId).get();
        ApplicationUser currUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("task", task);
        m.addAttribute("loggedInUser", currUser);
        return "editTaskView";
    }

    //delete
    @DeleteMapping("/task/delete/{id}")
    public RedirectView deleteTask(@PathVariable long id, Principal p) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        ScheduledTask taskToBeRemoved = scheduledTaskRepository.findById(id).get();
        if (!taskToBeRemoved.getGroupThisTaskBelongsTo().getOwner().equals(user)) {
            return new RedirectView("/fuckoff");
        }
        scheduledTaskRepository.delete(taskToBeRemoved);
        return new RedirectView("/dashboard");
    }



    @GetMapping("/addtask")
    public String getAddTask () {
        return "addtask";
    }
}
