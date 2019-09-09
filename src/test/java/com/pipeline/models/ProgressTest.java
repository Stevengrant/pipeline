package com.pipeline.models;

import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class ProgressTest {



    @Test
    public void setComplete() {
        Progress prog = new Progress();
        prog.setComplete(false);
        assertFalse(prog.isComplete);
        prog.setComplete(true);
        assertTrue(prog.isComplete);
    }

    @Test
    public void getSetDue() {
        Date due = new Date(System.currentTimeMillis()+10000);
        Progress prog = new Progress();
        prog.setDue(due);
        assertTrue(prog.getDue()!=null);
    }


    @Test
    public void getSetCompleteAt() {
        Date compAt = new Date(System.currentTimeMillis());
        Progress prog = new Progress();
        prog.setCompleteAt(compAt);
        assertTrue(prog.getCompleteAt().equals(compAt));
    }

    @Test
    public void getSetApplicationUser() {
        ApplicationUser applicationUser = new ApplicationUser();
        Progress prog = new Progress();
        prog.setApplicationUser(applicationUser);
        assertTrue(prog.getApplicationUser().equals(applicationUser));
    }

    @Test
    public void getSetTaskRelatedToThisProgress() {
        ScheduledTask task = new ScheduledTask();
        Progress prog = new Progress();
        prog.setTaskRelatedToThisProgress(task);
        assertTrue(prog.getTaskRelatedToThisProgress().equals(task));
    }
}