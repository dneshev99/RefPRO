package neshev.dimitar.project.Models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Referee {
    @Id
    String ID;

    private String firstName;
    private String middleName;
    private String lastName;
    private int age;
    private int height;

    public Referee(String firstName, String middleName, String lastName, int age, int height) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
    }

    public Referee() {}

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

}
