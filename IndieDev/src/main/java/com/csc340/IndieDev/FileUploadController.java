package com.csc340.IndieDev;

import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {
    // ... (other imports and autowired fields)
    @Autowired
    private UserService service;

    @PostMapping("/uploadProfilePic")
    public UploadResponse handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") String userId
    ) {
        String projectPath = System.getProperty("user.dir");
        String uploadDirectory = projectPath + "/IndieDev/src/main/resources/static/images/";

        // Create a unique file name using userId and set the file extension to .png
        String fileName = userId + "_profilepic.png";

        // Create the file path
        String filePath = uploadDirectory + fileName;

        try {
            // Check if the uploaded file is an image
            if (file.getContentType() != null && file.getContentType().startsWith("image/")) {
                // Convert and save the uploaded image as PNG
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (image != null) {
                    ImageIO.write(image, "png", new File(filePath));

                    // Update the user's profile picture in the database
                    User user = service.getUser(Long.parseLong(userId));
                    user.setProfile_picture("/images/" + fileName);
                    service.updateUser(user);

                    // Return the file name in the response
                    return new UploadResponse(fileName);
                } else {
                    System.err.println("Failed to read the image from the input stream.");
                }
            } else {
                System.err.println("Invalid file format. Only images are allowed.");
            }
        } catch (IOException e) {
            // Handle the exception (e.g., log error)
            e.printStackTrace();
        }

        // Return null if there was an error
        return null;
    }

    // Rest of the class remains the same

    // Response class to hold file name
    private static class UploadResponse {
        private final String fileName;

        public UploadResponse(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}



/*
@RestController
public class FileUploadController {
    // ... (other imports and autowired fields)
    @Autowired
    private UserService service;

    @PostMapping("/uploadProfilePic")
    public UploadResponse handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") String userId
    ) {
        String projectPath = System.getProperty("user.dir");
        String uploadDirectory = projectPath + "/IndieDev/src/main/resources/static/images/";

        // Create a unique file name using userId and original file extension
        String fileName = userId + "_profilepic" + getFileExtension(file.getOriginalFilename());

        // Create the file path
        String filePath = uploadDirectory + fileName;

        try {
            // Save the file to the server
            file.transferTo(new File(filePath));

            // Update the user's profile picture in the database
            User user = service.getUser(Long.parseLong(userId));
            user.setProfile_picture("/images/" + fileName);
            service.updateUser(user);

            // Return the file path and name in the response
            return new UploadResponse(fileName);
        } catch (IOException e) {
            // Handle the exception (e.g., log error)
            e.printStackTrace();
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    // Rest of the class remains the same

    // Response class to hold file name
    private static class UploadResponse {
        private final String fileName;

        public UploadResponse(String fileName) {
            this.fileName = fileName;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
 */



/*
@RestController
public class FileUploadController {
    @Autowired
    private UserService service;

    @PostMapping("/uploadProfilePic")
    public UploadResponse handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") String userId
    ) {
        String projectPath = System.getProperty("user.dir");
        String uploadDirectory = projectPath + "/IndieDev/src/main/resources/static/images/";

        // Create a unique file name using userId
        String fileName = userId + "_profilepic" + getFileExtension(file.getOriginalFilename());

        // Create the file path
        String filePath = uploadDirectory + fileName;

        try {
            // Save the file to the server
            file.transferTo(new File(filePath));

            // Update the user's profile picture in the database
            User user = service.getUser(Long.parseLong(userId));
            user.setProfile_picture("/images/" + fileName);
            service.updateUser(user);

            // Return the file path and name in the response
            return new UploadResponse(filePath, fileName);
        } catch (IOException e) {
            // Handle the exception (e.g., log error)
            e.printStackTrace();
            return null;
        }
    }


    /*
    @PostMapping("/uploadProfilePic")
    public UploadResponse handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {

        String projectPath = System.getProperty("user.dir");
        String uploadDirectory = projectPath + "/IndieDev/src/main/resources/static/images/";


        // Create a unique file name using userId
        String fileName = userId + "_profilepic" + getFileExtension(file.getOriginalFilename());

        // Create the file path
        String filePath = uploadDirectory + fileName;

        try {
            // Save the file to the server
            file.transferTo(new File(filePath));

            // Return the file path and name in the response
            return new UploadResponse(filePath, fileName);
        } catch (IOException e) {
            // Handle the exception (e.g., log error)
            e.printStackTrace();
            return null;
        }
    }
     */

    /*
    // Utility method to get the file extension
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex);
    }

    // Response class to hold file path and name
    private static class UploadResponse {
        private final String filePath;
        private final String fileName;

        public UploadResponse(String filePath, String fileName) {
            this.filePath = filePath;
            this.fileName = fileName;
        }

        public String getFilePath() {
            return filePath;
        }

        public String getFileName() {
            return fileName;
        }
    }
}
*/
