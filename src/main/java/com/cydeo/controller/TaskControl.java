package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String taskSave(@ModelAttribute("task") TaskDTO task){

        taskService.save(task);
        return "redirect:/task/create";
    }
}
