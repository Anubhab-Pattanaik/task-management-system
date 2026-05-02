package com.anubhab.taskmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anubhab.taskmanagementsystem.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
