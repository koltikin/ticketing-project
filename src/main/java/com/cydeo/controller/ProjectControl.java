package com.cydeo.controller;
import com.cydeo.dto.ProjectDTO;
import com.cydeo.service.UserService;
import com.cydeo.service.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/project")
public class ProjectControl {

    private final UserServiceImpl userService;

    @GetMapping("/create")
    public String projectCreate(Model model){

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManager());

        return "/project/create";
    }

}
