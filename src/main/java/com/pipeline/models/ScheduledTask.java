package com.pipeline.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScheduledTask {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    String name;
    String instructions;
    String pointOfContact;

    public ScheduledTask(){}
    public ScheduledTask(String name, String instructions, String pointOfContact){
        this.name = name;
        this.instructions = instructions;
        this.pointOfContact = pointOfContact;
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




}
