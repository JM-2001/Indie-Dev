package com.csc340.IndieDev.termsOfService;

import com.csc340.IndieDev.comment.Comment;
import com.csc340.IndieDev.comment.CommentRepository;
import com.csc340.IndieDev.post.PostRepository;
import com.csc340.IndieDev.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

public class TermsOfServiceService {

    @Autowired
    TermsOfServiceRepository tosRepo;

    public List<TermsOfService> getBody(String body){
        return tosRepo.findByBody(body);
    }
    public void updateTos(TermsOfService tos) {
        TermsOfService existing = tosRepo.findByBody(tos.getBody()).orElse(null);

        if (existing != null) {
            if (tos.getBody() != null) {
                existing.setBody(tos.getBody());
            }

            tosRepo.save(existing);
        }
    }


}
