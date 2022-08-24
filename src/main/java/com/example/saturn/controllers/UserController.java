package com.example.saturn.controllers;

import com.example.saturn.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping
    private ResponseEntity<List<User>> GetUser() {
        var userList = List.of(new User(1,"Cao Thiện Huân"));
        return ResponseEntity.ok(userList);
    }
}
