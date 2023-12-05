package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.comment.Comment;
import com.csc340.IndieDev.comment.CommentRepository;
import com.csc340.IndieDev.comment.CommentService;
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
public class TermsOfServiceController {

    @Autowired
    TermsOfServiceService tosService;
    @Autowired
    TermsOfServiceRepository tosRepo;

    @GetMapping("/post/{postId}/comments")
    @ResponseBody
    public List<TermsOfService> getTos(@PathVariable String body) {
        return tosRepo.findByBody(body);
    }

    @PostMapping("/post/{postId}/comment")
    public String addComment(
            @RequestParam String body,
            Model model
    ) {
        try {

            TermsOfService tos = tosRepo.findById(body).orElse(null);
            model.addAttribute("body", body);

            if (body == null) {
                return "redirect:/home";
            }

}
