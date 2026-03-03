package com.phonebook.models;

/*
 User — Value Object для данных пользователя.

 Передаётся в UserHelper.fillLoginRegisterForm().
 Использует fluent-style setters:

   User user = new User()
       .setEmail("test@mail.com")
       .setPassword("Pass123!");
*/
public class User {

    private String email;
    private String password;

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail()    { return email; }
    public String getPassword() { return password; }
}