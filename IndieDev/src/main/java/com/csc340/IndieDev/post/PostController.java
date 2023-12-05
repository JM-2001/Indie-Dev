package com.csc340.IndieDev.post;


import com.csc340.IndieDev.project.Project;
import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/like")
    public String likePost(@RequestParam Long postId) {
        postService.likePost(postId);
        return "redirect:/home";
    }


    @GetMapping("/createPost")
    public String createProject(){
        return "createPost";
    }

    @PostMapping("/savePost")
    public String saveProject(@RequestParam("file1") MultipartFile file1,
                              @ModelAttribute Post post, Principal principal) {

        String username = principal.getName();
        User user = userService.getUserByUserName(username);

        //Setting the user_id in the project table from the signed in user
        post.setUserId(user.getId());

        //First image
        if (file1 != null && !file1.isEmpty()) {

            //Setting the upload directory
            String uploadDir = "IndieDev/src/main/resources/static/images/";

            //setting target directory (cached images)
            String targetDir = "IndieDev/target/classes/static/images";

            //Getting the filename from the image
            String fileName = StringUtils.cleanPath(file1.getOriginalFilename());


            //Creating a unique file name from the userid that is creating it
            String[] newName = fileName.split("\\.");
            newName[0] = newName[0] + post.getUserId();
            String updatedName = newName[0] +"."+ newName[1];

            try {
                //Create a Path object to store directory from the given upload directory
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                Path targetPath = Paths.get(targetDir);
                if (!Files.exists(targetPath)) {
                    Files.createDirectories(targetPath);
                }

                //This is what combines the upload directory (to img folder) with the file name that is being uploaded
                Path filePath = uploadPath.resolve(updatedName);
                Path targetFilePath = targetPath.resolve(updatedName);

                //Get the contents of the file, copy it to the filepath location and (replace it if it already exists)
                Files.copy(file1.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(file1.getInputStream(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);

                //setting the visual string in project class from the name of the file
                post.setVisual(updatedName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Save the project by calling project service
        postService.savePost(post);
        return "redirect:/home";
    }

    @GetMapping("/deletePost")
    public String deletePost(@RequestParam Long postId) {
        // Validate if the user has the authority to delete the post (MOD or ADMIN)
        // Implement your authorization logic here...

        // If authorized, delete the post
        postService.deletePost(postId);

        return "redirect:/home";
    }

}

