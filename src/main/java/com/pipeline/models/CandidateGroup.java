package com.pipeline.models;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Set;

@Entity
public class CandidateGroup {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String groupName;

    @ManyToOne
    ApplicationUser owner;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "groupThatCandidatesBelongTo")
    public Set<ApplicationUser> candidatesInAGroup;

    @OneToMany
    Set<ScheduledTask> scheduledTasks;

    public void setTasksThatBelongToThisGroup(Set<ScheduledTask> tasksThatBelongToThisGroup) {
        this.tasksThatBelongToThisGroup = tasksThatBelongToThisGroup;
    }

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "groupThisTaskBelongsTo", cascade = CascadeType.ALL)
    Set<ScheduledTask> tasksThatBelongToThisGroup;

    //Contsructors
    public CandidateGroup() {}

    public CandidateGroup(String groupName, ApplicationUser owner) {
        this.groupName = groupName;
        this.owner = owner;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public Set<ScheduledTask> getScheduledTasks() {
        return scheduledTasks;
    }

    public void setScheduledTasks(ScheduledTask scheduledTask) {
        this.scheduledTasks.add(scheduledTask);
    }
    public ApplicationUser getOwner() {
        return owner;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ApplicationUser> getCandidatesInAGroup() {
        return candidatesInAGroup;
    }

    public void setCandidatesInAGroup(Set<ApplicationUser> candidatesInAGroup) {
        this.candidatesInAGroup = candidatesInAGroup;
    }


    public Set<ScheduledTask> getTasksThatBelongToThisGroup() {
        return tasksThatBelongToThisGroup;
    }

//    public void setTasksThatBelongToThisGroup(Set<ScheduledTask> tasksThatBelongToThisGroup) {
//        this.tasksThatBelongToThisGroup = tasksThatBelongToThisGroup;
//    }
}
