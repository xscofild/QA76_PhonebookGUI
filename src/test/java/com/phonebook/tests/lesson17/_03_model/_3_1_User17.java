package com.phonebook.tests.lesson17._03_model;

/*
 User17 — модель данных пользователя (Value Object).

 Используется для передачи email и password
 в форму login/registration.
 Не содержит логики — только хранит данные.
*/

public class _3_1_User17 {

    private String email;
    private String password;

    // Fluent-сеттеры позволяют создавать объект цепочкой:
    // new User17().setEmail(...).setPassword(...)
    public _3_1_User17 setEmail(String email) {
        this.email = email;
        return this;
    }

    public _3_1_User17 setPassword(String password) {
        this.password = password;
        return this;
    }

    // Геттеры используются helper-слоем при заполнении формы
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}