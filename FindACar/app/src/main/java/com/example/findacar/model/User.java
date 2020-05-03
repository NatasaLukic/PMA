package com.example.findacar.model;

public class User {
    private String name;
    private String surname;
    private String email;
    private String passHash;

    public User(String name, String surname, String email, String passHash) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.passHash = passHash;
    }
}
