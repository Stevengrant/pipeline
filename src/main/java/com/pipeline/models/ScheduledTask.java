package com.pipeline.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class ScheduledTask {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String name;
    List<String> instructions;
    String link;
    Set<String> pointOfContact;

    @ManyToOne
    CandidateGroup groupThisTaskBelongsTo;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "taskRelatedToThisProgress")
    Set<Progress> progressRelatedToThisTask;

    public ScheduledTask(){}

    public ScheduledTask(String name, List<String> instructions, Set<String> pointOfContact) {
        this.name = name;
        this.instructions = instructions;
        this.pointOfContact = pointOfContact;
    }

    public ScheduledTask(String name, List<String> instructions, Set<String> pointOfContact, String link) {
        this.name = name;
        this.instructions = instructions;
        this.pointOfContact = pointOfContact;
        this.link = link;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public Set<String> getPointOfContact() {
        return pointOfContact;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public void setPointOfContact(Set<String> pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public CandidateGroup getGroupThisTaskBelongsTo() {
        return groupThisTaskBelongsTo;
    }

    public Set<Progress> getProgressRelatedToThisTask() {
        return progressRelatedToThisTask;
    }

}
