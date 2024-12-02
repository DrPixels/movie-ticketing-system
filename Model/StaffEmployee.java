package Model;

import java.time.LocalDate;

public class StaffEmployee extends Employee{ 
    
    private final String role = "STAFF";
    
    private boolean isArchived;

    public StaffEmployee(String employeeId, 
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
    
        public StaffEmployee( String picturePath, 
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
        
     public StaffEmployee(String employeeId, String picturePath, 
            String firstName, 
            String middleName, 
            String lastName, 
            LocalDate birthday, 
            int age, 
            String gender, 
            String email, 
            String phoneNumber, 
            String username, 
            String password, boolean isArchived) {
        super(employeeId, picturePath, firstName, middleName, lastName, birthday, age, gender, email, phoneNumber, username, password);
        this.isArchived = isArchived;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }
    
    public boolean getIsArchived() {
        return isArchived;
    }
    
}