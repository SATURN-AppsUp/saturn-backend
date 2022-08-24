package com.example.saturn.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class User {
    private int id;
    private String name;
    private String email;
    private LocalDate dob;
    private Integer age;

    public User(int id, String name, String email, LocalDate dob, Integer age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }
    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.age = age;
    }

    public User() {
    }

}
