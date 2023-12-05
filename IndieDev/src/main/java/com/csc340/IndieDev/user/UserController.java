package com.csc340.IndieDev.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * WE HAVE NOT USED THIS CLASS YET, IF WE WANT TO RESTRUCTURE OUR MAPPINGS FROM ROUTER, THE USER ONES
 * WILL GO HERE
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping({"", "/"})
    public String userMenu(@RequestParam(name = "continue", required = false) String cont) {
        return "user/menu";
    }

    @GetMapping(value = {"/home"})
    public String home(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "user/home";
    }

    @GetMapping("/all")
    public String getAllUsers(Model model,
                              @RequestParam(name = "continue",required = false) String cont) {
        model.addAttribute("userList", service.getAllUsers());
        return "user/list-users";
    }

    @GetMapping("/id={id}")
    public String getUser(@PathVariable long id, Model model) {
        User user = service.getUser(id); // Replace with your actual method
        model.addAttribute("user", user);
        model.addAttribute("user", service.getUser(id));
        return "user/profile";
    }

    @GetMapping("/delete/id={id}")
    public String deleteUser(@PathVariable long id, Model model) {

        service.deleteUser(id);
        return "redirect:/user/all";
    }

    @PostMapping("/create")
    public String createUser(User user) {

        service.saveUser(user);
        return "redirect:/user/all";
    }


    @PostMapping("/update")
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/user/all";
    }

    @GetMapping("/new-user")
    public String newUserForm(Model model) {
        return "user/new-user";
    }

    @GetMapping("/update/id={id}")
    public String updateUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", service.getUser(id));
        return "user/update-user";
    }

}
