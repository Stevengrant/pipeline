package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.sql.Date;
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
    @Autowired
    ProgressRepository progressRepository;

    //create
    @PostMapping("/addtask/{groupId}")
    public RedirectView addTask(@PathVariable long groupId, Principal p, String nameOfTask, String instructions,
                                String poc, String taskLink, Date dueDate ){
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        //TODO: This might need some work
        //Checking to see if the person who's posting the new task is in fact the
        //owner of the group.
        if(!group.getOwner().equals(user)){
            return new RedirectView("error");
        }
        ScheduledTask task = new ScheduledTask(nameOfTask,instructions, poc, taskLink, dueDate, group );
        group.setScheduledTasks(task);
        scheduledTaskRepository.save(task);
        return new RedirectView("/groupView/" + groupId);
    }

    //update
    @PostMapping("/task/update/{taskId}")
    public RedirectView updateTask(@PathVariable long taskId, Principal p, String nameOfTask, String instructions,
                                   String poc, Date dueDate, String taskLink) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        ScheduledTask taskToBeUpdated = scheduledTaskRepository.findById(taskId).get();
        if (!taskToBeUpdated.getGroupThisTaskBelongsTo().getOwner().equals(user)) {
            return new RedirectView("/error");
        }
        if(nameOfTask != null){
            taskToBeUpdated.setName(nameOfTask);
        }
        if (taskLink != null) {
            taskToBeUpdated.setLink(taskLink);
        }
        if(instructions != null){
            taskToBeUpdated.setInstructions(instructions);
        }
        if(poc != null){
            taskToBeUpdated.setPointOfContact(poc);
        }

        if(dueDate != null) {
            taskToBeUpdated.setDueDate(dueDate);
        }
        scheduledTaskRepository.save(taskToBeUpdated);
        return new RedirectView("/groupView/" + taskToBeUpdated.getGroupThisTaskBelongsTo().getId());

    }
    @PostMapping("/task/markAsDone/{id}")
    public RedirectView markScheduledTaskAsDone(@PathVariable Long id, Principal p){
        ApplicationUser u = applicationUserRepository.findByUsername(p.getName());
        Set<Progress> prog = u.getProgressOfScheduledTasks();
        for (Progress progress : prog){
            if(progress.getId() == id){
                System.out.println(">>>===========-=>" + id + " : " + progress.getId());
                progress.setComplete(true);
                Date dateNow = new Date(System.currentTimeMillis());
                progress.setCompleteAt(dateNow);
                progressRepository.save(progress);
            }
        }
        u.setProgressOfScheduledTasks(prog);
        applicationUserRepository.save(u);

        return new RedirectView("/dashboard");
    }

    @GetMapping("/taskview/{groupId}")
    public String getTaskView(@PathVariable long groupId, Model m, Principal p){
        CandidateGroup group = candidateGroupRepository.findById(groupId).get();
        ApplicationUser currUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("loggedInUser", currUser);
        m.addAttribute("group", group);
        m.addAttribute("status", true);
        return "taskView";
    }

    @GetMapping("/taskview/edit/{taskId}")
    public String getTaskViewEdit(@PathVariable long taskId, Model m, Principal p){
        ScheduledTask task = scheduledTaskRepository.findById(taskId).get();
        ApplicationUser currUser = applicationUserRepository.findByUsername(p.getName());
        m.addAttribute("task", task);
        m.addAttribute("loggedInUser", currUser);
        m.addAttribute("status", false);
        return "taskView";
    }

    //delete
    @DeleteMapping("/task/delete/{id}")
    public RedirectView deleteTask(@PathVariable long id, Principal p) {
        ApplicationUser user = applicationUserRepository.findByUsername(p.getName());
        ScheduledTask taskToBeRemoved = scheduledTaskRepository.findById(id).get();
        if (!taskToBeRemoved.getGroupThisTaskBelongsTo().getOwner().equals(user)) {
            return new RedirectView("/error");
        }
        scheduledTaskRepository.delete(taskToBeRemoved);
        return new RedirectView("/dashboard");
    }



    @GetMapping("/addtask")
    public String getAddTask () {
        return "addtask";
    }
}
