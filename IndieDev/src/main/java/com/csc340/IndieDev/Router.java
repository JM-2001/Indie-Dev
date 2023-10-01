package com.csc340.IndieDev;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Router {


    @GetMapping("")
    public String welcome(){
        return "welcome";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/portfilo")
    public String portfilo() {
        return "portfolio";
    }

    @GetMapping("/createProject")
    public String createProject(){
        return "createProject";
    }
}
