package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/delete/{taskId}")
    public String taskDelete(@PathVariable("taskId") Long taskId){
        taskService.deleteById(taskId);
        return "redirect:/task/create";
    }

    @GetMapping("/update/{taskId}")
    public String taskCreate(@PathVariable("taskId") Long taskId,Model model){

        model.addAttribute("task",taskService.findById(taskId));
        model.addAttribute("projects",projectService.findAll());
        model.addAttribute("employees",userService.findAll());
        model.addAttribute("tasksList",taskService.findAll());

        return "/task/update";
    }

    @PostMapping("/update/{taskId}")
    public String taskUpdate(@PathVariable("taskId") Long taskId,@ModelAttribute("task") TaskDTO task){

        taskService.update(task,taskId);
        return "redirect:/task/create";
    }


}


