package com.pipeline.models;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class CandidateGroupTest {

    @Test
    public void getGroupName() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        assertTrue(candidateGroup.getGroupName().equals("groupName"));

    }

    @Test
    public void setGroupName() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        candidateGroup.setGroupName("newName");
        assertTrue(candidateGroup.getGroupName().equals("newName"));
    }

    @Test
    public void setOwner() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        assertTrue(applicationUser.equals(candidateGroup.getOwner()));
        candidateGroup.setOwner(new ApplicationUser());
        assertFalse(candidateGroup.getOwner().equals(applicationUser));
    }


    @Test
    public void getOwner() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        assertTrue(candidateGroup.getOwner().equals(applicationUser));
    }

    @Test
    public void getSetCandidatesInAGroup() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        Set<ApplicationUser> appUsers = new HashSet<>();

        for(int i = 0; i <10; i++ ){
            applicationUser= new ApplicationUser();
            appUsers.add(applicationUser);
        }
        candidateGroup.setCandidatesInAGroup(appUsers);
        assertTrue(candidateGroup.getCandidatesInAGroup().size() > 1);
    }


    @Test
    public void getSetTasksThatBelongToThisGroup() {
        ApplicationUser applicationUser= new ApplicationUser();
        CandidateGroup candidateGroup = new CandidateGroup("groupName",applicationUser);
        Set<ScheduledTask> tasks = new HashSet<>();
        ScheduledTask shedTask;
        for(int i = 0; i <10; i++ ){
            shedTask= new ScheduledTask();
            tasks.add(shedTask);
        }
        candidateGroup.setTasksThatBelongToThisGroup(tasks);
        assertTrue(candidateGroup.getTasksThatBelongToThisGroup().size() > 0);
    }
}