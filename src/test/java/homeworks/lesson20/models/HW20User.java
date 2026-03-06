package homeworks.lesson20.models;

public class HW20User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public HW20User setFirstName(String firstName) { this.firstName = firstName; return this; }
    public HW20User setLastName(String lastName)   { this.lastName = lastName;   return this; }
    public HW20User setEmail(String email)         { this.email = email;         return this; }
    public HW20User setPassword(String password)   { this.password = password;   return this; }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public String getEmail()     { return email; }
    public String getPassword()  { return password; }
}
