package com.csc340.IndieDev.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String password;
    private String role;
    private String profession;
    private String language;
    private String profile_picture;


    public User(Long id, String userName, String name, String password, String role, String profession, String language) {
        this.id = id;
        this.username = userName;
        this.name = name;
        this.role = role;
        this.password = password;
        this.profession = profession;
        this.language = language;
        this.profile_picture = profile_picture;
    }

}
