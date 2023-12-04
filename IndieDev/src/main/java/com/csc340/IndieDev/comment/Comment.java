package com.csc340.IndieDev.comment;
import jakarta.persistence.*;
import lombok.*;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "comment")
@NoArgsConstructor
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "post_id")
    private Long postId;


    @Column(name = "user_id")
    private Long userId;

    private String body;
    private Timestamp created_at;

    public Comment(Long commentId, Long postId, Long userId, String body, Timestamp createdAt) {
        this.commentId = commentId;
        this.postId = postId;
        this.userId = userId;
        this.body = body;
        this.created_at = createdAt;
    }
    public Comment(Long postId, Long userId, String body, Timestamp createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.body = body;
        this.created_at = createdAt;
    }



}
