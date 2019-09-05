package com.pipeline.models;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ScheduledTaskRepository extends JpaRepository<ScheduledTask, Long> {
}
