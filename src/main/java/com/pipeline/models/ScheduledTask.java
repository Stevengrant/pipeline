package com.pipeline.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class ScheduledTask {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String name;

    @Column(columnDefinition = "text")
    String instructions;

    String link;
    Date dueDate;

    @Column(columnDefinition = "text")
    String pointOfContact;

    @ManyToOne
    CandidateGroup groupThisTaskBelongsTo;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "taskRelatedToThisProgress", cascade = CascadeType.ALL)
    Set<Progress> progressRelatedToThisTask;

    public ScheduledTask(){}

    public ScheduledTask(String name, String instructions, String pointOfContact) {
        this.name = name;
        this.instructions = instructions;
        this.pointOfContact = pointOfContact;
    }

    public ScheduledTask(String name, String instructions, String pointOfContact, String link,
     Date dueDate, CandidateGroup groupThisTaskBelongsTo ) {
        this.name = name;
        this.instructions = instructions;
        this.pointOfContact = pointOfContact;
        this.link = link;
        this.dueDate = dueDate;
        this.groupThisTaskBelongsTo = groupThisTaskBelongsTo;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getPointOfContact() {
        return pointOfContact;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setPointOfContact(String pointOfContact) {
        this.pointOfContact = pointOfContact;
    }

    public CandidateGroup getGroupThisTaskBelongsTo() {
        return groupThisTaskBelongsTo;
    }

    public Set<Progress> getProgressRelatedToThisTask() {
        return progressRelatedToThisTask;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getLink() {
        return link;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setGroupThisTaskBelongsTo(CandidateGroup groupThisTaskBelongsTo) {
        this.groupThisTaskBelongsTo = groupThisTaskBelongsTo;
    }

    public void setProgressRelatedToThisTask(Set<Progress> progressRelatedToThisTask) {
        this.progressRelatedToThisTask = progressRelatedToThisTask;
    }
}
