package com.csc340.IndieDev;


import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class Router {

    @Autowired
    private UserService service;

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

    @GetMapping("/delete/id={id}")
    public String deleteUser(@PathVariable String id, Model model) {
        // Fetch the user based on the username
        User viewedUser = service.getUserByUserName(id);

        // Check if the user exists before attempting to delete
        if (viewedUser != null) {
            // Add the user to the model (if needed)
            model.addAttribute("user", viewedUser);

            // Perform the deletion logic
            service.deleteUser(Long.parseLong(id));
        }
        // Redirect to a suitable page after deletion or handle accordingly
        return "redirect:/home";
    }


    @GetMapping("/portfolio")
    public String portfolio() {
        return "portfolio";
    }

    @GetMapping("/createProject")
    public String createProject(){
        return "createProject";
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
}
