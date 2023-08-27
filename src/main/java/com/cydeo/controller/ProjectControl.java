package com.cydeo.controller;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectControl {

    private final UserService userService;
    private final ProjectService projectService;
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

        projectService.update(project);
        return "redirect:/project/create";
    }
    @GetMapping("/complete/{projectCode}")
    public String projectComplete(@PathVariable("projectCode") String projectCode){

        projectService.completeStatus(projectCode);
        return "redirect:/project/create";

    }


    @GetMapping("/manager/projects-status")
    public String projectStatus(Model model) {

        UserDTO manager = userService.findById("john@cydeo.com");
        List<ProjectDTO> projectList = projectService.getCountedProjectsList(manager);

        model.addAttribute("projectList", projectList);

        return "/manager/project-status";
    }

    @GetMapping("/manager/status-complete/{id}")
    public String statusComplete(@PathVariable("id") String id) {
        System.out.println(id);

        projectService.findById(id).setProjectStatus(Status.COMPLETE);


        return "redirect:/project/manager/projects-status";
    }


}
