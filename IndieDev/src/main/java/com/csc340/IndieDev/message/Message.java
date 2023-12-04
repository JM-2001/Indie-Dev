package com.csc340.IndieDev.message;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Time;
import java.sql.Timestamp;


@Entity
@Table(name = "message")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @Column(name = "user_id_author")
    private Long authorId;

    @Column(name = "user_id_to")
    private Long recipientId;

    private String body;
    private Timestamp createdAt;




}
