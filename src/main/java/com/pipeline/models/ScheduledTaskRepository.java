package com.pipeline.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ScheduledTaskRepository extends JpaRepository<ScheduledTask, Long> {
    public List<ScheduledTask> findByGroupThisTaskBelongsToId(long groupId);
}
