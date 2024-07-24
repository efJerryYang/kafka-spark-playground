package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scheduler")
public class SchedulerController {

    @Autowired
    private SchedulerService schedulerService;

    @PostMapping("/tasks")
    public void scheduleTask(@RequestBody Task task) {
        schedulerService.scheduleTask(task);
    }

    @GetMapping("/tasks")
    public List<Task> getAvailableTasks() {
        return schedulerService.getAvailableTasks();
    }
}