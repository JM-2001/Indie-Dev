package com.csc340.IndieDev.comment;


import com.csc340.IndieDev.post.PostRepository;
import com.csc340.IndieDev.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    public List<Comment> getCommentByPost(Long postId){
        return commentRepository.findByPostId(postId);
    }
    public void updateComment(Comment comment) {
        Comment existing = commentRepository.findById(comment.getCommentId()).orElse(null);

        if (existing != null) {
            if (comment.getBody() != null) {
                existing.setBody(comment.getBody());
            }

            commentRepository.save(existing);
        }
    }



}
