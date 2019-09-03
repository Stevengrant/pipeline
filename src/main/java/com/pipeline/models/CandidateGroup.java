package com.pipeline.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class CandidateGroup {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String groupName;
    ApplicationUser owner;

    @OneToMany
    List<ScheduledEvent> scheduledEvents;

    //Contructor
    public CandidateGroup() {}
    public CandidateGroup(String groupName, ApplicationUser owner, List<ScheduledEvent> scheduledEvents) {
        this.groupName = groupName;
        this.owner = owner;
        this.scheduledEvents = scheduledEvents;
    }
}
