package com.csc340.IndieDev.post;

import com.csc340.IndieDev.project.Project;
import com.csc340.IndieDev.project.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    PostRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Post> getAllPosts() {
        return repo.findAll();
    }

}
