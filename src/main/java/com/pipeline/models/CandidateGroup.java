package com.pipeline.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class CandidateGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String groupName;
    ApplicationUser owner;

    @OneToMany
    Set<Progress> scheduledTasks;

    //Contructor
    public CandidateGroup() {}
    public CandidateGroup(String groupName, ApplicationUser owner) {
        this.groupName = groupName;
        this.owner = owner;
//        this.scheduledTasks = scheduledTasks;
    }
    public ApplicationUser getOwner() {
        return owner;
    }
}
