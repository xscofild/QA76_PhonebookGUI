package homeworks.lesson19.models;

public class HW19User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public HW19User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public HW19User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public HW19User setEmail(String email) {
        this.email = email;
        return this;
    }

    public HW19User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
}