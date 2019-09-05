package com.pipeline.controllers;

import com.pipeline.models.Progress;
import com.pipeline.models.ProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;

@Controller
public class ProgressController {

    @Autowired
    ProgressRepository progressRepository;
    //update/complete
    @PostMapping("/progress/update/{progressTaskId}")
    public RedirectView updateProgress(@PathVariable long progressTaskId, boolean isComplete, Date due){
        Progress prog = progressRepository.findById(progressTaskId).get();
        prog.setComplete(isComplete);
        if(isComplete){
            prog.setCompleteAt(new Date(System.currentTimeMillis()));
        }
        if(due != null){
            prog.setDue(due);
        }
        progressRepository.save(prog);
        return new RedirectView("/dashboard");
    }
}
