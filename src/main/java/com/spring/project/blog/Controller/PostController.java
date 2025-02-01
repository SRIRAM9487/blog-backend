package com.spring.project.blog.Controller;

import com.spring.project.blog.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    private PostService service;

    @GetMapping("/post/")
    public String greet() {
        return "Hello from the post";
    }

    @GetMapping("/post/all/show")
    public ResponseEntity<?> showAllPost() {
        try {
            return new ResponseEntity<>(service.showAllPost(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/post/{id}/show")
    public ResponseEntity<?> showPostByUserId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showPostByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PostMapping("/user/{id}/post/add")
    public ResponseEntity<?> addPostByUserId(@PathVariable("id") Long id, @RequestParam String content) {
        try {
            return new ResponseEntity<>(service.addPostByUserId(id, content), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/post/{id}/update")
    public ResponseEntity<?> updatePostByUserId(@PathVariable("id") Long postId, @RequestParam String content) {
        try {
            return new ResponseEntity<>(service.updatePostByUserId(postId, content), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }


    @DeleteMapping("/user/{id}/post/delete")
    public ResponseEntity<?> deletePostByUserId(@PathVariable("id") Long UserId, @RequestParam Long postId) {
        try {
            return new ResponseEntity<>(service.deletePostByUserId(UserId, postId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @DeleteMapping("/user/{id}/post/all/delete")
    public ResponseEntity<?> deleteAllPostByUserId(@PathVariable("id") Long UserId) {
        try {
            return new ResponseEntity<>(service.deleteAllPostByUserId(UserId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("ERROR : " + e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}
