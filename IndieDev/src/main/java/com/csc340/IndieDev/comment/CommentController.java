package com.csc340.IndieDev.comment;


import com.csc340.IndieDev.post.Post;
import com.csc340.IndieDev.post.PostRepository;
import com.csc340.IndieDev.post.PostService;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserRepository;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.desktop.SystemEventListener;
import java.security.Principal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {


    @Autowired
    CommentService commentService;
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @Autowired
    PostService postService;
    @Autowired
    PostRepository postRepository;

    @GetMapping("/post/{postId}/comments")
    @ResponseBody
    public List<Comment> getComments(@PathVariable Long postId) {
        // Retrieve comments for the specified post
        return commentRepository.findByPostId(postId);
    }
    @PostMapping("/post/{postId}/comment")
    public String addComment(
            @PathVariable Long postId,
            @RequestParam String body,
            Model model
    ) {
        try {
            // Get the currently authenticated user's ID
            Long userId = getCurrentUserId();

            // Rest of the code remains the same...
            Post post = postRepository.findById(postId).orElse(null);
            model.addAttribute("post", post);

            if (post == null) {
                return "redirect:/home";
            }

            Timestamp ts = new Timestamp(System.currentTimeMillis());
            Comment comment = new Comment(postId, userId, body, ts);
            comment.setUserId(userId);
            comment.setPostId(postId);
            comment.setBody(body);
            comment.setCreated_at(new Timestamp(System.currentTimeMillis()));

            commentRepository.save(comment);

            // Redirect to the same page after creating the comment
            return "redirect:/home";
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the error appropriately, for example, redirect to an error page
            return "redirect:/error";
        }
    }


    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof org.springframework.security.core.userdetails.User) {
            org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

            // Assuming you have a field 'id' in your User entity
            User userDetails = userRepository.findByUsername(user.getUsername()).orElse(null);

            if (userDetails != null) {
                return userDetails.getId();
            }
        }
        return null;
    }


    }



