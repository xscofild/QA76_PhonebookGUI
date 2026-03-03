package com.phonebook.models;

/*
 User
 Модель данных пользователя (Value Object).
 Используется для передачи email и password в форму.
*/

public class User {

    private String email;
    private String password;

    // Fluent setters
    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    // Getters

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}