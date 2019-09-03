package com.pipeline.root.models;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduledEventRepository extends JpaRepository<ScheduledEvent, Long> {
}
