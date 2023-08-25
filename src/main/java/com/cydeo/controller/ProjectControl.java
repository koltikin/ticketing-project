package com.cydeo.controller;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.impl.ProjectServiceImpl;
import com.cydeo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectControl {

    private final UserServiceImpl userService;
    private final ProjectServiceImpl projectService;
    @GetMapping("/create")
    public String projectCreate(Model model){

     model.addAttribute("project", new ProjectDTO());
     model.addAttribute("managers", userService.findManager());
     model.addAttribute("projects", projectService.findAll());

        return "/project/create";
    }

    @PostMapping("/save")
    public String projectSave(@ModelAttribute("project") ProjectDTO project){
        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/delete/{projectCode}")
    public String projectDelete(@PathVariable("projectCode") String projectCode){
        projectService.deleteById(projectCode);
        return "redirect:/project/create";
    }

    @GetMapping("/update/{projectCode}")
    public String projectUpdate(@PathVariable("projectCode") String projectCode, Model model){

        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("managers", userService.findManager());
        model.addAttribute("projects", projectService.findAll());

        return "/project/update";
    }
    @PostMapping("/update")
    public String projectUpdate(@ModelAttribute("project") ProjectDTO project){

        projectService.updateStatus(project,project.getProjectCode());
        projectService.update(project);
        return "redirect:/project/create";

    }


}
