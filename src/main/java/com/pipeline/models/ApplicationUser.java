package com.pipeline.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    long id;

    @Column(unique = true)
    String username;

    String password;
    String firstName;
    String lastName;

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isAdmin;
    //This represents the relationship between an admin and their owned groups
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "owner")
    Set<CandidateGroup> candidateGroup;

    //This represents the relationship between and candidate and their candidate group
    @ManyToOne
    public CandidateGroup groupThatCandidatesBelongTo;

    public void setProgressOfScheduledTasks(Set<Progress> progressOfScheduledTasks) {
        ProgressOfScheduledTasks = progressOfScheduledTasks;
    }
    @OneToMany (fetch = FetchType.EAGER, mappedBy = "applicationUser", cascade = CascadeType.ALL)
    Set<Progress> ProgressOfScheduledTasks;

    //Constructor
    public ApplicationUser () {}

    //Constructor for Candidate Users
    public ApplicationUser (String username, String password, String firstName, String lastName, CandidateGroup candidateGroup) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.groupThatCandidatesBelongTo = candidateGroup;
        this.isAdmin = false;
    }
    //Constructor for Admin Users
    public ApplicationUser (String username, String password, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isAdmin = true;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<CandidateGroup> getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(CandidateGroup candidateGroup) {
        this.candidateGroup.add(candidateGroup);
    }

    public Set<Progress> getProgressOfScheduledTasks() {
        return ProgressOfScheduledTasks;
    }

    public long getId() {
        return id;
    }
}
