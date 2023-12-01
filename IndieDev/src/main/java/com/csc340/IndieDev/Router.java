package com.csc340.IndieDev;


import com.csc340.IndieDev.project.Project;
import com.csc340.IndieDev.project.ProjectService;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class Router {

    @Autowired
    private UserService service;

    @Autowired
    private ProjectService projectService;

    @GetMapping("")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    //Mapping for home, stores the signed in user as currentUser to be accessed in thymeleaf
    @GetMapping(value = {"/home"})
    public String home(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("currentUser", name);
        return "home";
    }


    /**This actually redirects by the username, not id -- leaving it like this for now bc it works
     * Creates 2 users, the one that is currently signed in, and the user being viewed
     * Creating 2 users allow for elements in the front end to only be displayed if viewing
     * someone else's profile, ex report, send friend request etc.
     */
    @GetMapping("/id={id}")
    public String getUserProfile(@PathVariable String id, Model model) {

        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = service.getUserByUserName(currentUsername);
        User viewedUser = service.getUserByUserName(id);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", viewedUser);
        //model.addAttribute("user", service.getUserByUserName(id));
        return "profile";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, Model model) {

        //get the user by id
        User viewedUser = service.getUser(id);

        //if user is valid,
        if (viewedUser != null) {
            //add an attribute called "user" to viewedUser --> allows user functions to be called in thymeleaf
            model.addAttribute("user", viewedUser);

            //uses the userservice class to call delete user on the id passed in the url
            service.deleteUser(id);
        }

        // Redirect to a suitable page after deletion or handle accordingly
        return "redirect:/home";
    }

    @PostMapping("/update")
    public String updateUser(User user) {
        service.updateUser(user);
        return "redirect:/home";
    }

    @GetMapping("/portfolio")
    public String portfolio(Model model, Principal principal) {

        String username = principal.getName();
        User user = service.getUserByUserName(username);

        // Assuming you have a method to get the user's projects in your UserService
        List<Project> projects = service.getProjectsByUserId(user.getId());

        model.addAttribute("user", user);
        model.addAttribute("projects", projects);

        return "portfolio";
    }

    @GetMapping("/profile-example")
    public String profileExample(){
        return "profile-example";
    }

    @GetMapping("/modview-profile")
    public String modviewProfile(){
        return "modview-profile";
    }

    @GetMapping("/adminview-profile")
    public String adminProfile(){
        return "adminview-profile";
    }

    @GetMapping("/reportsheet")
    public String reportSheet(){
        return "reportsheet";
    }

    @GetMapping("/notifications")
    public String notification(){
        return "notifications";
    }

    @GetMapping("/chats")
    public String chats(){
        return "chat";
    }

    @PostMapping("/changeAuthorization/{id}")
    public String changeAuthorizationLevel(@PathVariable Long id, Model model) {
        User viewedUser = service.getUser(id);
        viewedUser.setRole("LOCKEDUSER");
        service.updateUser(viewedUser);
        return "redirect:/home";  // Redirect to the user profile page or any other page
    }
}
