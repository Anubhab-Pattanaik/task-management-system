package com.anubhab.taskmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.anubhab.taskmanagementsystem.dto.TaskResponseDTO;
import com.anubhab.taskmanagementsystem.entity.Task;
import com.anubhab.taskmanagementsystem.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDTO> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseDTO> responseList = new ArrayList<>();

        for(Task task: tasks) {
            TaskResponseDTO dto = new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getAssignedTo() != null ? task.getAssignedTo().getName(): null
            );
            responseList.add(dto);
        }
        return responseList;
    }
}
