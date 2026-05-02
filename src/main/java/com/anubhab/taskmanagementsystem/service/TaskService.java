package com.anubhab.taskmanagementsystem.service;

import org.springframework.stereotype.Service;

import com.anubhab.taskmanagementsystem.dto.TaskRequestDTO;
import com.anubhab.taskmanagementsystem.entity.Task;
import com.anubhab.taskmanagementsystem.entity.TaskStatus;
import com.anubhab.taskmanagementsystem.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequestDTO dto) {
        Task task = new Task();

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) {
            task.setStatus(TaskStatus.valueOf(dto.getStatus()));
        } else {
            task.setStatus(TaskStatus.TODO);
        }
        return taskRepository.save(task);
    }
    
}
