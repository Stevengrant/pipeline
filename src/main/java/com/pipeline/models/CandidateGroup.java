package com.pipeline.models;

import javax.persistence.*;
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

    ApplicationUser owner;

    @OneToMany
    List<ScheduledTask> scheduledTasks;

    //Contructor
    public CandidateGroup() {}
    public CandidateGroup(String groupName, ApplicationUser owner, List<ScheduledTask> scheduledTasks) {
        this.groupName = groupName;
        this.owner = owner;
        this.scheduledTasks = scheduledTasks;
    }
}
