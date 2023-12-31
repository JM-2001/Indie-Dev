package com.csc340.IndieDev.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    PostRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public List<Post> getAllPosts() {
        return repo.findAll();
    }


    public void likePost(Long postId) {
        Optional<Post> postOptional = repo.findById(postId);
        postOptional.ifPresent(post -> {
            post.setLike_count(post.getLike_count() + 1);
            repo.save(post);
        });
    }

    public void deletePost(Long postId) {
        repo.deleteById(postId);
    }

    public void savePost(Post post) {
        repo.save(post);
    }

    public List<Post> getPostByUserId(long userId){
        return repo.findByUserId(userId);
    }

    public Optional<Post> getPostById(long postId) {
        return repo.findPostByPostId(postId);

    }

    public void lockPost(long postId) {
        Optional<Post> postOptional = repo.findById(postId);
        postOptional.ifPresent(post -> {
            post.setLockedPost(true);
            repo.save(post);
        });
    }

    public void unlockPost(long postId) {
        Optional<Post> postOptional = repo.findById(postId);
        postOptional.ifPresent(post -> {
            post.setLockedPost(false);
            repo.save(post);
        });

    }

}
