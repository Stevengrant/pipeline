package com.pipeline.root.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Group {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String groupName;
    ApplicationUser owner;

    @OneToMany
    List<ScheduledEvent> scheduledEvents;
}
