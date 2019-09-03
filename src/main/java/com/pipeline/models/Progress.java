package com.pipeline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

    public Progress(){}
    public Progress (boolean isComplete, Date created, Date due, Date completeAt){
        this.isComplete = isComplete;
        this.created = created;
        this.due = due;
        this.completeAt = completeAt;
    }
}
