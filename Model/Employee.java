package Model;

import java.time.LocalDate;

public class Employee {
    // Fields
    private String employeeId;
    private String picturePath;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate birthday;
    private int age;
    private String gender;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;

    // Constructor
    public Employee(String employeeId, String picturePath, String firstName, String middleName, String lastName,
                LocalDate birthday, int age, String gender, String email, String phoneNumber,
                String username, String password) {
        this.employeeId = employeeId;
        this.picturePath = picturePath;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }
    
    //For Registration Data
    public Employee(String picturePath, String firstName, String middleName, String lastName,
                LocalDate birthday, int age, String gender, String email, String phoneNumber,
                String username, String password) {
        this.picturePath = picturePath;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
    }

    // employeeId
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // picturePath
    public String getPicturePath() {
        return picturePath;
    }
    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    // firstName
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // middleName
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // lastName
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // birthday
    public LocalDate getBirthday() {
        return birthday;
    }
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    // age
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

    // gender
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }

    // email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // phoneNumber
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // username
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    // password
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}