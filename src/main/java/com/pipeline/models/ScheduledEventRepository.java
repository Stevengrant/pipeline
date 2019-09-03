package com.pipeline.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Long> {
}
