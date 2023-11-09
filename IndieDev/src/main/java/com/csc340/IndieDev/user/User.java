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


    public User(String userName, String name, String password, String role, String profession, String language) {
        this.username = userName;
        this.name = name;
        this.role = role;
        this.password = password;
        this.profession = profession;
        this.language = language;
    }

}
