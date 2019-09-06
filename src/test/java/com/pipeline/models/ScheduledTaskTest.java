package com.pipeline.models;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class ScheduledTaskTest {


    @Test
    public void getSetName() {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setName("name");
        assertTrue(scheduledTask.getName().equals("name"));
    }

    @Test
    public void getSetInstructions() {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setInstructions("name");
        assertTrue(scheduledTask.getInstructions().equals("name"));
    }

    @Test
    public void getPointOfContact() {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.setPointOfContact("name");
        assertTrue(scheduledTask.getPointOfContact().equals("name"));
    }
    @Test
    public void getGroupThisTaskBelongsTo() {
        CandidateGroup group = new CandidateGroup();
        ScheduledTask scheduledTask = new ScheduledTask(
                "Name",
                "Instructions",
                "Point of Contact",
                "Link",
                new Date(System.currentTimeMillis()),
                group
                );
        assertTrue(scheduledTask.getGroupThisTaskBelongsTo().equals(group));
    }

    @Test
    public void getProgressRelatedToThisTask() {
        CandidateGroup group = new CandidateGroup();
        ScheduledTask scheduledTask = new ScheduledTask(
                "Name",
                "Instructions",
                "Point of Contact",
                "Link",
                new Date(System.currentTimeMillis()),
                group
        );
    }

    @Test
    public void getSetDueDate() {
        Date due = new Date(System.currentTimeMillis()+30000);
        CandidateGroup group = new CandidateGroup();
        ScheduledTask scheduledTask = new ScheduledTask(
                "Name",
                "Instructions",
                "Point of Contact",
                "Link",
                due,
                group
        );
        assertTrue(scheduledTask.getDueDate().equals(due));
        Date newDue = new Date(System.currentTimeMillis()+15000);
        scheduledTask.setDueDate(newDue);
        assertFalse(scheduledTask.getDueDate().equals(due));
        assertTrue(scheduledTask.getDueDate().equals(newDue));
    }
}