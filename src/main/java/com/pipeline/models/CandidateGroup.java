package com.pipeline.models;

import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.beans.FeatureDescriptor;
import java.util.List;

@Entity
public class CandidateGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String groupName;
    public ApplicationUser getOwner() {
        return owner;
    }

    @ManyToOne
    ApplicationUser owner;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "groupThatCandidatesBelongTo")
    Set<ApplicationUser> candidatesInAGroup;

    @OneToMany
    Set<ScheduledTask> scheduledTasks;

    @OneToMany (fetch = FetchType.EAGER, mappedBy = "groupThisTaskBelongsTo")
    Set<ScheduledTask> tasksThatBelongToThisGroup;



    //Contructor
    public CandidateGroup() {}
    public CandidateGroup(String groupName, ApplicationUser owner, List<ScheduledTask> scheduledTasks) {
        this.groupName = groupName;
        this.owner = owner;
        this.scheduledTasks = scheduledTasks;
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

    public void setScheduledTasks(Set<ScheduledTask> scheduledTasks) {
        this.scheduledTasks = scheduledTasks;
    }
}
