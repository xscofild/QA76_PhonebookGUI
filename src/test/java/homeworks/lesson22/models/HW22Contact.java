package homeworks.lesson22.models;

/*
 HW22Contact — модель данных контакта (Value Object).

 Передаётся в HW22ContactHelper.fillContactForm().

 Fluent-style — создание объекта цепочкой:
   new HW22Contact().setName("Serdar").setSurname("Kerimov").setPhoneNumber("4915123456789");

 Поля могут быть null — HW22ContactHelper.type() пропустит такое поле.
 Это позволяет создавать частично заполненные контакты для негативных тестов.
*/
public class HW22Contact {

    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private String address;
    private String description;

    // Каждый setter возвращает this — для поддержки fluent-style цепочки.
    public HW22Contact setName(String name) {
        this.name = name;
        return this;
    }

    public HW22Contact setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public HW22Contact setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public HW22Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public HW22Contact setAddress(String address) {
        this.address = address;
        return this;
    }

    public HW22Contact setDescription(String description) {
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
                "HW22Contact{name='%s', surname='%s', phone='%s', email='%s', address='%s', description='%s'}",
                name, surname, phoneNumber, email, address, description
        );
    }
}
