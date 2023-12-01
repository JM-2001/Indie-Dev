package com.csc340.IndieDev.post;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

}
