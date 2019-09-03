package com.pipeline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScheduledEvent {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String instructions;
    String pointOfContact;

}
