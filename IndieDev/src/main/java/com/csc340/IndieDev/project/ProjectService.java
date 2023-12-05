package com.csc340.IndieDev.project;


import com.csc340.IndieDev.user.User;
import com.csc340.IndieDev.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<Project> getAllProjects() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public Project getProject(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id
     */
    public void deleteProject(long id) {
        repo.deleteById(id);
    }


    public void deleteProject(Project project) {
        repo.delete(project);
    }

    /**
     * Save user entry.
     *
     *
     */
    public void saveProject(Project project) {
        repo.save(project);
    }

    /**
     * Update existing user.
     *
     *
     */
    public void updateProject(Project project) {
        Project existing = repo.getReferenceById(project.getProject_id());

        if (project.getName() != null) {
            existing.setName(project.getName());
        }

        if(project.getBody() != null){
            existing.setBody(project.getBody());
        }

        if(project.getVisual() != null){
            existing.setVisual(project.getVisual());
        }

        repo.save(existing);
    }


}
