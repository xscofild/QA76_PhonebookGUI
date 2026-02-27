package com.phonebook.tests.lesson17._03_model;

/*
 Contact17 — модель данных контакта (Value Object).

 Используется для передачи данных в форму добавления контакта.
 Не содержит логики, только хранит значения полей.
*/

public class _3_2_Contact17 {

    // Поля контакта
    String name;
    String surname;
    String phoneNumber;
    String email;
    String address;
    String description;

    // Fluent-сеттеры позволяют создавать объект цепочкой:
    // new Contact17().setName(...).setSurname(...)
    public _3_2_Contact17 setName(String name) {
        this.name = name;
        return this;
    }

    public _3_2_Contact17 setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public _3_2_Contact17 setPhoneNumber(String phone) {
        this.phoneNumber = phone;
        return this;
    }

    public _3_2_Contact17 setEmail(String email) {
        this.email = email;
        return this;
    }

    public _3_2_Contact17 setAddress(String address) {
        this.address = address;
        return this;
    }

    public _3_2_Contact17 setDescription(String description) {
        this.description = description;
        return this;
    }

    // Геттеры используются helper-слоем для заполнения формы

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getDescription() {
        return description;
    }
}