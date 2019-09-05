package com.pipeline.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Progress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    boolean isComplete;
    Date due;
    Date completeAt;

    @ManyToOne
    ApplicationUser applicationUser;

    @ManyToOne
    ScheduledTask taskRelatedToThisProgress;

    public Progress(){}
    public Progress (boolean isComplete, Date due, Date completeAt){
        this.isComplete = isComplete;
        this.due = due;
        this.completeAt = completeAt;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public Date getCompleteAt() {
        return completeAt;
    }

    public void setCompleteAt(Date completeAt) {
        this.completeAt = completeAt;
    }

    public ApplicationUser getApplicationUser() {
        return applicationUser;
    }

    public void setApplicationUser(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    public ScheduledTask getTaskRelatedToThisProgress() {
        return taskRelatedToThisProgress;
    }

    public void setTaskRelatedToThisProgress(ScheduledTask taskRelatedToThisProgress) {
        this.taskRelatedToThisProgress = taskRelatedToThisProgress;
    }


}
