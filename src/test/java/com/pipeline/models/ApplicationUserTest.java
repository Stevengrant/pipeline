package com.pipeline.models;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class ApplicationUserTest {
    // The fact that you have so much duplicated code across tests means it belongs in an @Before method.
    ApplicationUser adminUser;
    ApplicationUser candidateUser;
    static final String username ="USER NAME";
    static final String password ="PASSWORD";
    static final String firstName = "FIRSTNAME";
    static final String lastName = "LASTNAME";
    @Before
    public void setUp() {
        adminUser = new ApplicationUser( username,  password,  firstName,  lastName);
        candidateUser = new ApplicationUser(username, password, firstName, lastName, new CandidateGroup());
    }

    @Test
    public void isAdmin() {
        assertTrue(adminUser.isAdmin());
        assertFalse(candidateUser.isAdmin());
    }

    @Test
    public void setProgressOfScheduledTasks() {
        Set<Progress> progressOfScheduledTasks = new HashSet<>();
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        progressOfScheduledTasks.add(new Progress());
        candidateUser.setProgressOfScheduledTasks(progressOfScheduledTasks);
        // could at least assert that it's equal to 4
        assertTrue(candidateUser.ProgressOfScheduledTasks.size() > 0);
    }

    @Test
    public void getFunctionsGetTheThingsTheyAreSupposedTo() {
        // should avoid using assertTrue where possible
        assertEquals("username should be what was originally set", username, candidateUser.getUsername());
        assertEquals(password, candidateUser.getPassword());
        assertEquals(firstName, candidateUser.getFirstName());
        assertEquals(lastName, candidateUser.getLastName());
    }

}