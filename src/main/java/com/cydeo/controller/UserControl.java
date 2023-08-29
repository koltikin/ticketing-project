package com.cydeo.controller;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.RoleService;
import com.cydeo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
public class UserControl {

    private final RoleService roleService;
    private final UserService userService;

    @GetMapping("/create")
    public String createUser(Model model){
        model.addAttribute("user", new UserDTO());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("userList",userService.findAll());

        return "/user/create";
    }
    @PostMapping("/save")
    public String saveUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult bindingResult,Model model ){
        if(bindingResult.hasErrors()){
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("userList",userService.findAll());
            return "/user/create";
        }
        userService.save(user);
        return "redirect:/user/create";
    }

    @GetMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username ){
        userService.deleteById(username);
        return "redirect:/user/create";
    }

    @GetMapping("/update/{username}")
    public String updateUser(@PathVariable("username") String username, Model model){

        model.addAttribute("user",userService.findById(username));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("userList",userService.findAll());
        return "/user/update";

    }
    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") UserDTO user){
        userService.update(user);
        return "redirect:/user/create";

    }


}
