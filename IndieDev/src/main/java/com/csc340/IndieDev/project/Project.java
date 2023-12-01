package com.csc340.IndieDev.project;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "project")
@NoArgsConstructor
@Getter
@Setter
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column(name = "user_id")
    private Long userId;


    //Gained from form
    private String name;
    private String classification;
    private String status;
    private String visual;
    private String visual2;
    private String timespent;
    private String body;


    public Project(Long project_id, Long userId, String name, String classification, String status, String visual, String visual2, String body, String timespent){
        this.project_id = project_id;
        this.userId = userId;

        this.name = name;
        this.classification = classification;
        this.status = status;
        this.visual = visual;
        this.visual2 = visual2;
        this.body = body;
        this.timespent = timespent;
    }
}
