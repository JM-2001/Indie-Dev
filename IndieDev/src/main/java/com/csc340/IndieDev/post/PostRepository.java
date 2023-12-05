package com.csc340.IndieDev.post;

import com.csc340.IndieDev.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    Optional<Post> findPostByPostId(Long postId);

}
