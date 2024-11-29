package Model;

import java.time.LocalDate;

public class AdminUser extends User{ 
    
    private final String role = "ADMIN";

    public AdminUser(String userId, 
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
        super(userId, picturePath, firstName, middleName, lastName, birthday, age, gender, email, phoneNumber, username, password);
    }
    
        public AdminUser( String picturePath, 
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
