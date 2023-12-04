package com.csc340.IndieDev.report;

/*
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.project.Project;
import com.csc340.IndieDev.post.Post;
 */

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "report")
@NoArgsConstructor
@Getter
@Setter
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    @Column(name = "reported_user_id", nullable = false)
    private Long reportedUserId;

    @Column(name = "reported_project_id")
    private Long reportedProjectId;

    @Column(name = "reported_post_id")
    private Long reportedPostId;

    @Column(name = "reporter_user_id")
    private Long reporterUserId;

    private String reportClassification;

    private String reportDescription;

    @CreationTimestamp
    private LocalDateTime creationDate;

    public Report(Long reportId, Long reportedUserId, Long reportedProjectId, Long reportedPostId, Long reporterUserId, String reportClassification, String reportDescription, LocalDateTime creationDate) {
        this.reportId = reportId;
        this.reportedUserId = reportedUserId;
        this.reportedProjectId = reportedProjectId;
        this.reportedPostId = reportedPostId;
        this.reporterUserId = reporterUserId;
        this.reportClassification = reportClassification;
        this.reportDescription = reportDescription;
        this.creationDate = creationDate;
    }

// Getters and setters
}