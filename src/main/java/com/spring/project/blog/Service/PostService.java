package com.spring.project.blog.Service;

import com.spring.project.blog.Repository.CommentRepository;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final Logger LOG = LoggerFactory.getLogger(PostService.class);


    @Autowired
    private UserRepository userrepo;

    @Autowired
    private PostRepository postrepo;

    @Autowired
    private CommentRepository commentrepo;

    public void idCheker(Long id){
        if(id == null){
            throw new IllegalArgumentException("The id cannot be null");
        }
        if(id < 0){
            throw new IllegalArgumentException("The id cannot be negative");
        }
    }

    public Object showAllPost() {
    }

    public Object showPostByUserId(Long id) {
    }

    public Object addPostByUserId(Long id, String content) {
    }

    public Object updatePostByUserId(Long userId, Long postId, String content) {
    }

    public Object deletePostByUserId(Long userId, Long postId) {
    }

    public Object deleteAllPostByUserId(Long userId) {
        return null;
    }
}
