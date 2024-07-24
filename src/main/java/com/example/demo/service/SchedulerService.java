package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchedulerService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    public void scheduleTask(Task task) {
        taskRepository.save(task);
        kafkaProducerService.sendMessage("taskTopic", task.getId());
    }

    public List<Task> getAvailableTasks() {
        return taskRepository.findAll();
    }
}