package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Plan;
import com.example.new_retro_project.models.Task;
import com.example.new_retro_project.pojo.TaskPojo;
import com.example.new_retro_project.repository.PlanRepository;
import com.example.new_retro_project.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/plan")
@CrossOrigin("http://localhost:8080")
public class PlanRestController {

    @Autowired
    private PlanRepository planRepository;

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping(value="/{retrospectiveId}", produces={"application/json","application/xml"})
    public ResponseEntity<Plan> getPlanByRetrospectiveId(@PathVariable("retrospectiveId") String retrospectiveId) {
        Collection<Plan> plans = planRepository.findAll();
        Plan plan = new Plan();
        boolean found = false;
        for (Plan p: plans) {
            if (p.getRetrospectiveId().equals(retrospectiveId)) {
                plan = p;
                found = true;
                break;
            }
        }
        if (!found) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok().body(plan);
        }
    }

    @GetMapping(value="/tasks/{id}", produces={"application/json","application/xml"})
    public ResponseEntity<Collection<Task>> getTasksForPlan(@PathVariable("id") String id) {
        if (!planRepository.existsById(id))
            return ResponseEntity.notFound().build();
        else {
            Collection<Task> tasks = taskRepository.findAll();
            List<Task> tasksForPlan = new ArrayList<>();
            for (Task t: tasks) {
                if (t.getPlanId().equals(id)) {
                    tasksForPlan.add(t);
                }
            }
            return ResponseEntity.ok().body(tasksForPlan);
        }
    }

    @PostMapping(consumes={"application/json","application/xml"},
            produces={"application/json","application/xml"})
    public ResponseEntity<Task> insertTask(@RequestBody TaskPojo taskPojo) {
        Task task = new Task(taskPojo);
        taskRepository.insert(task);
        URI uri = URI.create("/tasks/" + task.getPlanId());
        return ResponseEntity.created(uri).body(task);
    }
}
