package com.example.new_retro_project.controllers;

import com.example.new_retro_project.models.Plan;
import com.example.new_retro_project.models.Task;
import com.example.new_retro_project.pojo.PlanPojo;
import com.example.new_retro_project.pojo.TaskPojo;
import com.example.new_retro_project.repository.PlanRepository;
import com.example.new_retro_project.repository.RetrospectiveRepository;
import com.example.new_retro_project.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataMongoTest
class PlanRestControllerTest {

    @InjectMocks
    private PlanRestController controller;

    @Mock
    private PlanRepository planRepository;

    @Mock
    private TaskRepository taskRepository;

    private PlanPojo plan1 = new PlanPojo("630f33a957172c1700901455");
    private PlanPojo plan2 = new PlanPojo("630f33a957172c1700901457");
    private List<Plan> plans = new ArrayList<>(Arrays.asList(new Plan(plan1), new Plan(plan2)));

    private TaskPojo task1 = new TaskPojo("630f33a957172c1700901458", "desc");
    private TaskPojo task2 = new TaskPojo("630f33a957172c1700901458", "desc2");
    private List<Task> tasks = new ArrayList<>(Arrays.asList(new Task(task1), new Task(task2)));


    @Test
    void getPlanByRetrospectiveId() {
        when(planRepository.findAll()).thenReturn(plans);

        ResponseEntity<Plan> result = controller.getPlanByRetrospectiveId("630f33a957172c1700901455");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(plan1.getRetrospectiveId().equals(result.getBody().getRetrospectiveId()));
    }

    @Test
    void getTasksForPlan() {

        when(planRepository.existsById("630f33a957172c1700901458")).thenReturn(true);
        when(taskRepository.findAll()).thenReturn(tasks);

        ResponseEntity<Collection<Task>> result = controller.getTasksForPlan("630f33a957172c1700901458");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertThat(task1.getPlanId().equals(result.getBody().stream().findFirst().orElse(null).getPlanId()));
    }

    @Test
    void insertTask() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(taskRepository.insert(any(Task.class))).thenReturn(new Task(task1));

        ResponseEntity<Task> result = controller.insertTask(task1);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertThat(task1.getPlanId().equals(result.getBody().getPlanId()));
    }
}