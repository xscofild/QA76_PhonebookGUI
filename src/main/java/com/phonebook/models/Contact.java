package com.phonebook.models;

/*
 Contact
 Модель данных контакта (Value Object).
 Используется для передачи данных в форму.
 Логики не содержит.
*/

public class Contact {

    // Поля модели должны быть private
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String description;

    // Fluent setters (builder-style)
    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public Contact setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Contact setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public Contact setAddress(String address) {
        this.address = address;
        return this;
    }

    public Contact setDescription(String description) {
        this.description = description;
        return this;
    }

    // Getters

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