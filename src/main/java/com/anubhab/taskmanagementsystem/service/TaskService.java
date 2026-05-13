package com.anubhab.taskmanagementsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.anubhab.taskmanagementsystem.dto.TaskResponseDTO;
import com.anubhab.taskmanagementsystem.entity.Task;
import com.anubhab.taskmanagementsystem.entity.TaskStatus;
import com.anubhab.taskmanagementsystem.exception.ResourceNotFoundException;
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
            responseList.add(convertToDTOResponse(task));
        }
        return responseList;
    }

    public Optional<TaskResponseDTO> getTaskById(Long id) {
        Optional<Task> taskById = taskRepository.findById(id);
        if(taskById.isPresent()) {
            Task task = taskById.get();
            return Optional.of(convertToDTOResponse(task));
        }
        else {
            throw new ResourceNotFoundException("Task with id " + id + " not found");
        }
    }

    private TaskResponseDTO convertToDTOResponse(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getStatus().name(),
            task.getAssignedTo() != null ? task.getAssignedTo().getName(): null
        );
    }

    private Task covertToEntity(TaskResponseDTO taskResponseDto) {
        Task task = new Task();
        task.setTitle(taskResponseDto.getTitle());
        task.setDescription(taskResponseDto.getDescription());
        task.setStatus(TaskStatus.valueOf(taskResponseDto.getStatus()));
        return task;
    }
}
