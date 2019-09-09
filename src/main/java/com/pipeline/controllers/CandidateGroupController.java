package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.GroupSequence;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CandidateGroupController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CandidateGroupRepository candidateGroupRepository;

    //create
    @PostMapping("/addgroup")
    public RedirectView addGroup(String groupName, Principal p){
        ApplicationUser owner = applicationUserRepository.findByUsername(p.getName());
        if(!owner.isAdmin()){
            return new RedirectView("/error");
        }
        CandidateGroup newGroup = new CandidateGroup(groupName,owner);
        candidateGroupRepository.save(newGroup);
        return new RedirectView("/dashboard");
    }

    @GetMapping ("/creategroup")
    public String getCreateGroup(Principal p) {
        return "creategroup";
    }

    @GetMapping("/groupView")
    public String getGroupView(Principal p, Model m) {
//        m.addAttribute("group", )
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "groupView";
    }

    @GetMapping("/groupView/{groupId}")
    public String getGroupView(@PathVariable long groupId, Principal p, Model m) {
        m.addAttribute("group", candidateGroupRepository.findById(groupId).get());
        m.addAttribute("loggedInUser", applicationUserRepository.findByUsername(p.getName()));
        return "groupView";
    }
}
