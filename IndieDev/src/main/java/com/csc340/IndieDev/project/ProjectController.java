package com.csc340.IndieDev.project;

import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;


@Controller
@RequestMapping("/")
public class ProjectController {

    @Autowired
    public ProjectService projectService;

    @Autowired
    public UserService userService;


    @GetMapping("/createProject")
    public String createProject(Model model){
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userService.getUserByUserName(currentUsername);

        model.addAttribute("currentUser", currentUser);

        return "createProject";
    }

    @PostMapping("/saveProject")
    public String saveProject(@RequestParam("file1") MultipartFile file1,
                              @RequestParam("file2") MultipartFile file2,
                              @ModelAttribute Project project, Principal principal) {

        String username = principal.getName();
        User user = userService.getUserByUserName(username);

        //Setting the user_id in the project table from the signed in user
        project.setUserId(user.getId());

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
            newName[0] = newName[0] + project.getUserId();
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
                project.setVisual(updatedName);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Second image
        if (file2 != null && !file2.isEmpty()) {
            //Setting the upload directory
            String uploadDir = "IndieDev/src/main/resources/static/images/";

            //setting target directory (cached images)
            String targetDir = "IndieDev/target/classes/static/images";

            //Getting the filename from the image
            String fileName = StringUtils.cleanPath(file2.getOriginalFilename());

            //Creating a unique file name from the userid that is creating it
            String[] newName2 = fileName.split("\\.");
            newName2[0] = newName2[0] + project.getUserId();
            String updatedName2 = newName2[0] +"."+ newName2[1];

            try {
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                Path targetPath = Paths.get(targetDir);
                if (!Files.exists(targetPath)) {
                    Files.createDirectories(targetPath);
                }

                Path filePath = uploadPath.resolve(updatedName2);
                Path targetFilePath = targetPath.resolve(updatedName2);

                Files.copy(file2.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                Files.copy(file2.getInputStream(), targetFilePath, StandardCopyOption.REPLACE_EXISTING);

                project.setVisual2(updatedName2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //Save the project by calling project service
        projectService.saveProject(project);
        return "redirect:/portfolio/"+username;
    }

}





