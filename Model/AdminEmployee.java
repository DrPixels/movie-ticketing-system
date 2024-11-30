package Model;

import java.time.LocalDate;

public class AdminEmployee extends Employee{ 
    
    private final String role = "ADMIN";

    public AdminEmployee(String employeeId, 
            String picturePath, 
            String firstName, 
            String middleName, 
            String lastName, 
            LocalDate birthday, 
            int age, 
            String gender, 
            String email, 
            String phoneNumber, 
            String username, 
            String password) {
        super(employeeId, picturePath, firstName, middleName, lastName, birthday, age, gender, email, phoneNumber, username, password);
    }
    
        public AdminEmployee( String picturePath, 
            String firstName, 
            String middleName, 
            String lastName, 
            LocalDate birthday, 
            int age, 
            String gender, 
            String email, 
            String phoneNumber, 
            String username, 
            String password) {
        super(picturePath, firstName, middleName, lastName, birthday, age, gender, email, phoneNumber, username, password);
    }
    
    public String getRole() {
        return role;
    }
    
}
