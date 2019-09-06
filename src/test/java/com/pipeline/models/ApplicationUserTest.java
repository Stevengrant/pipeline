package com.pipeline.models;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ApplicationUserTest {

    @Test
    public void isAdmin() {
        String username ="USER NAME";
        String password ="PASSWORD";
        String firstName = "FIRSTNAME";
        String lastName = "LASTNAME";
        ApplicationUser applicationUser = new ApplicationUser( username,  password,  firstName,  lastName);
        assertTrue(applicationUser.isAdmin());

         username ="USER NAME";
         password ="PASSWORD";
         firstName = "FIRSTNAME";
         lastName = "LASTNAME";
        CandidateGroup candidateGroup = new CandidateGroup();
        applicationUser = new ApplicationUser( username,  password,  firstName,  lastName, candidateGroup);
        assertFalse(applicationUser.isAdmin());
    }

    @Test
    public void setProgressOfScheduledTasks() {
        String username ="USER NAME";
        String password ="PASSWORD";
        String firstName = "FIRSTNAME";
        String lastName = "LASTNAME";
        CandidateGroup candidateGroup = new CandidateGroup();
        ApplicationUser applicationUser = new ApplicationUser( username,  password,  firstName,  lastName, candidateGroup);
        Set<Progress> progressOfScheduledTasks = new HashSet<>();
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        applicationUser.setProgressOfScheduledTasks(progressOfScheduledTasks);
        assertTrue(applicationUser.ProgressOfScheduledTasks.size() > 0);
    }

    @Test
    public void getFunctionsGetTheThingsTheyAreSupposedTo() {
        String username ="USER NAME";
        String password ="PASSWORD";
        String firstName = "FIRSTNAME";
        String lastName = "LASTNAME";
        CandidateGroup candidateGroup = new CandidateGroup();
        ApplicationUser applicationUser = new ApplicationUser( username,  password,  firstName,  lastName, candidateGroup);
        assertTrue(applicationUser.getUsername().equals("USER NAME"));
        assertTrue(applicationUser.getPassword().equals("PASSWORD"));
        assertTrue(applicationUser.getFirstName().equals("FIRSTNAME"));
        assertTrue(applicationUser.getLastName().equals("LASTNAME"));
    }

}