package com.pipeline.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    boolean isComplete;
    Date created;
    Date due;
    Date completeAt;

    @ManyToOne
    ApplicationUser applicationUser;

    @ManyToOne
    ScheduledTask taskRelatedToThisProgress;

    public Progress(){}
    public Progress (boolean isComplete, Date created, Date due, Date completeAt){
        this.isComplete = isComplete;
        this.created = created;
        this.due = due;
        this.completeAt = completeAt;
    }
}
