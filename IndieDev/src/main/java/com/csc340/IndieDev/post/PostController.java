package com.csc340.IndieDev.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/like")
    public String likePost(@RequestParam Long postId) {
        postService.likePost(postId);
        return "redirect:/home";  // Redirect back to the home page after the like is processed
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam Long postId) {
        // Validate if the user has the authority to delete the post (MOD or ADMIN)
        // Implement your authorization logic here...

        // If authorized, delete the post
        postService.deletePost(postId);

        return "redirect:/home";
    }

}

