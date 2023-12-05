package com.csc340.IndieDev;


import com.csc340.IndieDev.comment.Comment;
import com.csc340.IndieDev.comment.CommentService;
import com.csc340.IndieDev.message.Message;
import com.csc340.IndieDev.message.MessageService;
import com.csc340.IndieDev.post.Post;
import com.csc340.IndieDev.post.PostService;
import com.csc340.IndieDev.project.Project;
import com.csc340.IndieDev.project.ProjectService;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class Router {

    @Autowired
    private UserService service;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @GetMapping("")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    //Mapping for home, stores the signed in user as currentUser to be accessed in thymeleaf
    @GetMapping(value = {"/home"})
    public String home(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = service.getUserByUserName(name);
        model.addAttribute("currentUser", currentUser);

        List<Post> posts = postService.getAllPosts();

        for (Post post : posts) {
            List<Comment> comments = commentService.getCommentByPost(post.getPostId());
            model.addAttribute("comments_" + post.getPostId(), comments);
        }

        model.addAttribute("posts", posts);

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

        List<Post> posts = postService.getPostByUserId(viewedUser.getId());
        for (Post post : posts) {
            List<Comment> comments = commentService.getCommentByPost(post.getPostId());
            model.addAttribute("comments_" + post.getPostId(), comments);
        }

        model.addAttribute("posts", posts);

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", viewedUser);
        //model.addAttribute("user", service.getUserByUserName(id));
        return "profile";
    }

    @GetMapping(value = {"/dashboard"})
    public String dashboard(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = service.getUserByUserName(name);
        List<User> userList = service.getAllUsers();

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("userList", userList);

        return "dashboard";
    }


    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
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

    @GetMapping("/portfolio/{id}")
    public String portfolio(@PathVariable String id, Model model, Principal principal) {

        String username = principal.getName();
        User currentUser = service.getUserByUserName(username);

        User viewedUser = service.getUserByUserName(id);


        List<Project> projects = service.getProjectsByUserId(viewedUser.getId());

        model.addAttribute("currentUser", currentUser);
        model.addAttribute("user", viewedUser);
        model.addAttribute("projects", projects);

        return "portfolio";
    }



    @GetMapping({"/chats", "/chats/{userId}"})
    public String chats(@PathVariable(name = "userId", required = false) Long userId, Model model){
        // Get current user
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = service.getUserByUserName(name);
        model.addAttribute("currentUser", currentUser);

        // Get list of msgs
        List<Message> messages = messageService.getMessagesByUser(currentUser);
        model.addAttribute("messages", messages);

        //Get list of users
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);

        if (userId != null) {
            // If a user ID is provided, fetch messages for that user
            User selectedUser = service.getUser(userId);
            List<Message> messageHistory = messageService.getMessagesBetweenUsers(currentUser, selectedUser);
            model.addAttribute("selectedUser", selectedUser);
            model.addAttribute("messageHistory", messageHistory);
            model.addAttribute("recipientId", selectedUser.getId());
            System.out.println("Current User ID: " + currentUser.getId());
            System.out.println("Selected User ID: " + selectedUser.getId());
            System.out.println("Message History Size: " + messageHistory.size());
        }
        return "chat";
    }

    @RequestMapping(value = "/changeAuthorization/{id}", method = RequestMethod.POST)
    public String changeAuthorizationLevel(@PathVariable Long id, Model model) {
        User viewedUser = service.getUser(id);
        viewedUser.setRole("LOCKEDUSER");
        service.updateUser(viewedUser);
        return "redirect:/home";
    }


    @GetMapping("/accountwarning")
    public String accountInfoWarning(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = service.getUserByUserName(username);

        // Add both account name and username to the model
        model.addAttribute("accountName", currentUser.getName());
        model.addAttribute("accountUsername", currentUser.getUsername());

        return "accountInfoScreen";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Perform validation and registration logic
        service.saveUser(user);
        return "redirect:/login"; // Redirect to the login page after successful registration
    }

    @PostMapping("/unlockAccount/{username}")
    public String unlockAccount(@PathVariable long username) {
        User viewedUser = service.getUser(username);
        viewedUser.setRole("USER");
        service.updateUser(viewedUser);

        return "redirect:/dashboard";
    }


}
