package com.spring.project.blog.Service;

import com.spring.project.blog.Model.UserModel;
import com.spring.project.blog.Repository.CommentRepository;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
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

    public Object showAllUser() {
        try {
            LOG.info("The user data is beign fetched");
            return  userrepo.findAll();
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error while fetching the user data ");
        }
    }

    public Object showUserById(Long id) {
        idCheker(id);
        try {
            return userrepo.findById(id).orElseThrow(() -> {LOG.error("User not found");throw new IllegalArgumentException("User not found");});
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object showCommentByUser(Long id) {
        try {
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object showPostByUser(Long id) {
        try {
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object addUser(UserModel user) {
        try {
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object updateUser(Long id, UserModel user) {

        try {
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }

    public Object deleteUser(Long id) {

        try {
        } catch (Exception e) {
            LOG.error("ERROR : ", e.getMessage());
            throw new RuntimeException("Error : " + e.getMessage());
        }
    }
}
