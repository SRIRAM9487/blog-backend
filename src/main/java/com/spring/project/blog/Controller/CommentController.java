package com.spring.project.blog.Controller;

import com.spring.project.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentController {

    @Autowired
    private CommentService service;

    @GetMapping("/")
    public String greet() {
        return "hell from the comment";
    }

    @GetMapping("/comment/all/show")
    public ResponseEntity<?> showAllComments() {
        try {
            return new ResponseEntity<>(service.showAllComments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/comment/{id}/show")
    public ResponseEntity<?> showCommentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showCommentById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/parentcomment/{id}/replies/{id}/show")
    public ResponseEntity<?> showRepliesById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showRepliesById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/post/{id}/replies/{id}/show")
    public ResponseEntity<?> showrepliesByPost(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showRepliesByPost(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/user/{id}/post/replies/show")
    public ResponseEntity<?> showCommentByUserId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showCommentsByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/user/post/comment/add")
    public ResponseEntity<?> addCommentByPost(@PathVariable("id") Long user_id, @RequestParam Long post_id, @RequestParam String content) {
        try {
            return new ResponseEntity<>(service.addCommentByPost(user_id,post_id,content), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/user/{id}/parentComment/comment/add")
    public ResponseEntity<?> addCommentByParentComment(@PathVariable("id") Long user_id, @RequestParam Long parent_id, @RequestParam String content) {
        try {
            return new ResponseEntity<>(service.addCommentByParentComment(user_id, parent_id, content), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/comment/{id}/udpate")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @RequestParam String content) {
        try {
            return new ResponseEntity<>(service.updateComment(id, content), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/comment/{id}/delete")
    public ResponseEntity<?> deleteCommentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.deleteCommentById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/parent/{id}/comment/delete")
    public ResponseEntity<?> deleteCommentOfParentComentById(@PathVariable("id") Long parentComment_id, @RequestParam Long comment_id) {
        try {
            return new ResponseEntity<>(service.deleteCommentOfParentById(parentComment_id, comment_id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/post/{id}/comment/delete")
    public ResponseEntity<?> deleteCommentByPost(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.deleteCommentByPost(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/comment/{id}/comment/delete")
    public ResponseEntity<?> deleteCommentByCustomerId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.deleteCommentByCustomerId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}