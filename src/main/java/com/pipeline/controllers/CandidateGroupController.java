package com.pipeline.controllers;

import com.pipeline.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CandidateGroupController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    CandidateGroupRepository candidateGroupRepository;
    @PostMapping("/addgroup")
    public RedirectView addGroup(String name, Principal p){
        String groupName = name;
        ApplicationUser owner = applicationUserRepository.findByUsername(p.getName());
        List<ScheduledTask> events = new ArrayList<>();

        CandidateGroup newGroup = new CandidateGroup(groupName,owner,events);
        candidateGroupRepository.save(newGroup);
        return new RedirectView("/dashboard");

    }
}
