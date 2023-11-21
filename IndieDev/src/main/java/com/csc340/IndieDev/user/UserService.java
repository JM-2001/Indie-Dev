package com.csc340.IndieDev.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author csc340-f23
 *
 *
 */
@Service
public class UserService {

    @Autowired
    UserRepository repo;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Get all users
     *
     * @return the list of users.
     */
    public List<User> getAllUsers() {
        return repo.findAll();
    }

    /**
     * Find one user by ID.
     *
     * @param id
     * @return the user
     */
    public User getUser(long id) {
        return repo.getReferenceById(id);
    }

    /**
     * Delete user by ID.
     *
     * @param id
     */
    public void deleteUser(long id) {
        repo.deleteById(id);
    }


    public void deleteUser(User user) {
        repo.delete(user);
    }

    /**
     * Save user entry.
     *
     * @param user
     */
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repo.save(user);
    }


    /**
     * Update existing user.
     *
     * @param user
     */
    public void updateUser(User user) {
        User existing = repo.getReferenceById(user.getId());

        if (user.getProfile_picture() != null && !user.getProfile_picture().isEmpty()) {
            existing.setProfile_picture(user.getProfile_picture());
        }

        if (user.getUsername() != null) {
            existing.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getName() != null) {
            existing.setName(user.getName());
        }
        if (user.getRole() != null) {
            existing.setRole(user.getRole());
        }
        if(user.getProfession() != null){
            existing.setProfession(user.getProfession());
        }

        repo.save(existing);
    }



    public User getUserByUserName(String username) {
        return repo.findByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException(username + "not found"));

    }

}
