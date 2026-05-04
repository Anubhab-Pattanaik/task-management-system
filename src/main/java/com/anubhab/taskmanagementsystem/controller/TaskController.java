package com.anubhab.taskmanagementsystem.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.anubhab.taskmanagementsystem.dto.TaskResponseDTO;
import com.anubhab.taskmanagementsystem.exception.ResourceNotFoundException;
import com.anubhab.taskmanagementsystem.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private TaskService taskService;
    
    @GetMapping
    public List<TaskResponseDTO> fetchAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO fetchTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id).orElseThrow(() -> new ResourceNotFoundException("Task with id " + id + " not found"));
    }

}
