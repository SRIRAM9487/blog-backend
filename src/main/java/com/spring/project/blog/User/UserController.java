package com.spring.project.blog.User;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/user/")
    public String greet() {
        return "Hello from the user";
    }

    @GetMapping("/user/all/show")
    public ResponseEntity<?> showAllUser() {
        try {
            return new ResponseEntity<>(service.showAllUser(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/user/{id}/show")
    public ResponseEntity<?> showUserById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.showUserById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>( e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserModel user) {
        try {
            return new ResponseEntity<>(service.register(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserModel user) {
        try {
            return new ResponseEntity<>(service.login(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }

    @PutMapping("/user/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody UserModel user) {
        try {
            return new ResponseEntity<>(service.updateUser(id, user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }


    @DeleteMapping("/user/{id}/delete")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.deleteUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_GATEWAY);
        }
    }
}