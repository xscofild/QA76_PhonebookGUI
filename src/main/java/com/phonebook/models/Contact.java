package com.phonebook.models;

/*
 Contact — модель данных контакта (Value Object).

 Передаётся в ContactHelper.fillContactForm().

 Fluent-style — создание объекта цепочкой:
   new Contact().setName("John").setSurname("Wick").setPhoneNumber("1234567890");

 Поля могут быть null — ContactHelper.type() пропустит такое поле.
 Это позволяет создавать частично заполненные контакты для негативных тестов.
*/
public class Contact {

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String description;

    // Каждый setter возвращает this — для поддержки fluent-style цепочки.
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

    public String getName()        { return name; }
    public String getSurname()     { return surname; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail()       { return email; }
    public String getAddress()     { return address; }
    public String getDescription() { return description; }

    // toString() — для читаемого вывода в логах: logger.info("Contact: {}", contact)
    @Override
    public String toString() {
        return String.format(
                "Contact{name='%s', surname='%s', phone='%s', email='%s', address='%s', description='%s'}",
                name, surname, phoneNumber, email, address, description
        );
    }
}
