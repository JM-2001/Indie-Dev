package com.csc340.IndieDev.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long postId;

    @Column(name = "user_id")
    public Long userId;


    //Gained from form
    private String visual;
    private String body;
    private int like_count;
    private Timestamp created_at;

    private boolean lockedPost;

    public Post(Long postId, Long userId, String visual, String body, int like_count, Timestamp created_at, boolean lockedPost) {
        this.postId = postId;
        this.userId = userId;
        this.visual = visual;
        this.body = body;
        this.like_count = like_count;
        this.created_at = created_at;
        this.lockedPost = lockedPost;
    }
}
