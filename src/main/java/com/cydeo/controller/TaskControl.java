package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@AllArgsConstructor
@Controller
@RequestMapping("/task")
public class TaskControl {
    ProjectService projectService;
    UserService userService;
    TaskService taskService;
    @GetMapping("/create")
    public String taskCreate(Model model){

        model.addAttribute("task",new TaskDTO());
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findAll());
        model.addAttribute("tasksList",taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/save")
    public String taskSave(TaskDTO task){

        taskService.save(task);
        return "redirect:/task/create";
    }
    @GetMapping("/delete/{taskId}")
    public String taskDelete(@PathVariable("taskId") Long taskId){
        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String taskUpdate(@PathVariable("taskId") Long taskId,Model model){

        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findAll());
        model.addAttribute("tasksList",taskService.findAll());

        return "/task/update";
    }

    @PostMapping("/update/{id}")
    public String taskUpdate(TaskDTO task){

        taskService.update(task);
        return "redirect:/task/create";
    }

    @GetMapping("/status")
    public String taskStatus(Model model){

        return "/task/status-update";
    }

    @GetMapping("/employee/pending-tasks")
    public String pendingTasks(Model model){

        model.addAttribute("tasks",taskService.findAllTasksNotCompleted());

        return "/task/pending-tasks";
    }

    @GetMapping("/employee/update/{id}")
    public String pendingTaskUpdate(@PathVariable("id") Long id, Model model){

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("statuses", Status.values());
        model.addAttribute("tasks",taskService.findAll());

        return "/task/status-update";

    }

    @PostMapping("/employee/update/{id}/{assignedDate}")
    public String pendingTaskUpdateSave(TaskDTO task){

        taskService.updateStatus(task);


        return "redirect:/task/employee/pending-tasks";

    }


}


