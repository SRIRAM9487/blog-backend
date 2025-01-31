package com.spring.project.blog.Service;

import com.spring.project.blog.Model.UserModel;
import com.spring.project.blog.Repository.CommentRepository;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserRepository userrepo;

    @Autowired
    private PostRepository postrepo;

    @Autowired
    private CommentRepository commentrepo;

    public void idCheker(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The id cannot be null");
        }
        if (id < 0) {
            throw new IllegalArgumentException("The id cannot be negative");
        }
    }

    public void checker(String name, String Email) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name missing");
        }
        if (Email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email missing");
        }
        if (!Email.contains("@gmail.com")) {
            throw new IllegalArgumentException("Email invalid");
        }
    }

    public Object showAllUser() {
        try {
            LOG.info("The user data is beign fetched");
            return userrepo.findAll();
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error while fetching the user data ");
        }
    }

    public Object showUserById(Long id) {
        try {
            idCheker(id);
            return userrepo.findById(id).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalArgumentException("User not found");
            });
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }


    public Object addUser(UserModel user) {
        try {
            checker(user.getName(), user.getEmail());
            LOG.info("User added successfully");
            return userrepo.save(user);
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    @Transactional
    public Object updateUser(Long id, UserModel user) {
        try {
            idCheker(id);
            checker(user.getName(), user.getEmail());
            if (
                    userrepo.existsByEmail(user.getEmail())
                            && userrepo.findByEmail(user.getEmail()).getId().equals(user.getId())){
                throw new IllegalArgumentException("The email already exists");
            }
            return userrepo.save(user);
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object deleteUser(Long id) {

        try {
            idCheker(id);
            UserModel user = userrepo.findById(id).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalArgumentException("User not found");
            });
            userrepo.delete(user);
            return user;
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }
}
