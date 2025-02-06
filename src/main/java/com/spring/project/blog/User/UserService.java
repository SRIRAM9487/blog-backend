package com.spring.project.blog.User;

import com.spring.project.blog.Comment.CommentRepository;
import com.spring.project.blog.Post.PostRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

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

    public List<UserModel> showAllUser() {
        try {
            LOG.info("The user data is being fetched");
            return userrepo.findAll();
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error while fetching the user data ");
        }
    }

    public UserModel showUserById(Long id) {
        try {
            idCheker(id);
            UserModel user = userrepo.findById(id).orElseThrow(() -> {
                LOG.error("user not found");
                throw new IllegalArgumentException("User not found");
            });
            LOG.info("The user with " + id + " has been fetched successfully");
            return user;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }


    public UserModel addUser(UserModel user) {
        try {
            checker(user.getName(), user.getEmail());
            user.setPassword(encoder.encode(user.getPassword()));
            UserModel new_user = userrepo.save(user);
            LOG.info("User added successfully");
            return new_user;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    @Transactional
    public UserModel updateUser(Long id, UserModel user) {
        try {
            idCheker(id);
            checker(user.getName(), user.getEmail());

            UserModel updated_user = userrepo.findById(id).orElseThrow(() -> {
                LOG.error("user not found");
                throw new IllegalArgumentException("User not found");
            });
            UserModel existing_user = userrepo.findByEmail(user.getEmail());
            if (existing_user != null && !existing_user.getId().equals(id)) {
                throw new IllegalArgumentException("The email already exists");
            }
            updated_user.setName(user.getName());
            updated_user.setEmail(user.getEmail());
            updated_user.setContent(user.getContent());
            return userrepo.save(updated_user);
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public UserModel deleteUser(Long id) {
        try {
            idCheker(id);
            UserModel user = userrepo.findById(id).orElseThrow(() -> {
                LOG.error("User not found");
                throw new IllegalArgumentException("User not found");
            });
            userrepo.delete(user);
            LOG.info("user " + id + " deleted successfully");
            return user;
        } catch (Exception e) {
            LOG.error("ERROR : {}", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

}