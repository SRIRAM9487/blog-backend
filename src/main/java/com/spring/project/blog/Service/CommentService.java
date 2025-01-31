package com.spring.project.blog.Service;

import com.spring.project.blog.Repository.CommentRepository;
import com.spring.project.blog.Repository.PostRepository;
import com.spring.project.blog.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final Logger LOG = LoggerFactory.getLogger(CommentService.class);


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

    public Object showAllComments() {

    }

    public Object showCommentById(Long id) {
    }

    public Object showRepliesById(Long id) {
    }

    public Object showRepliesByPost(Long id) {
    }

    public Object showCommentsByUserId(Long id) {
    }

    public Object addCommentByParentComment(Long userId, Long parentId, String content) {
    }

    public Object updateComment(Long id, String content) {
    }

    public Object deleteCommentById(Long id) {
    }

    public Object deleteCommentOfParentById(Long parentCommentId, Long commentId) {
    }

    public Object deleteCommentByPost(Long id) {
    }

    public Object deleteCommentByCustomerId(Long id) {
    }

    public Object addCommentByPost(Long userId, Long postId, String content) {
    }
}
