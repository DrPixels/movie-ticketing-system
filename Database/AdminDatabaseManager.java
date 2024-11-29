package Database;

//import static Database.DatabaseManager.connect;
//import static Database.DatabaseManager.connection;
import Model.AdminUser;
import helper.Helper;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.UUID;
import java.time.LocalDate;

public class AdminDatabaseManager extends DatabaseManager{
    
        public static boolean registerAdmin(AdminUser admin) {
        
        connect();
        
        //Fix, auto-generated
        String userId = UUID.randomUUID().toString();
        
        if(!addAdminDataToUsers(userId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to users. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!addAdminDataToAuthentication(userId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to authentication. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!addAdminDataToRoles(userId, admin)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error adding admin data to roles. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true; 
    }
        
    private static boolean addAdminDataToUsers(String userId, AdminUser admin) {
        
        String registerAdminQuery = "INSERT INTO users VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(registerAdminQuery);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(admin.getPicturePath()));
            
            Date birthDay = Date.valueOf(admin.getBirthday());
            
            prepSt.setString(1, userId);
            prepSt.setBlob(2, is);
            prepSt.setString(3, admin.getPicturePath());
            prepSt.setString(4, admin.getFirstName());
            prepSt.setString(5, admin.getMiddleName());
            prepSt.setString(6, admin.getLastName());
            prepSt.setDate(7, birthDay);
            prepSt.setInt(8, admin.getAge());
            prepSt.setString(9, admin.getGender());
            prepSt.setString(10, admin.getEmail());
            prepSt.setString(11, admin.getPhoneNumber());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
        
    private static boolean addAdminDataToAuthentication(String userId, AdminUser admin) {
        
        String query = "INSERT INTO authentication VALUES(?,?,?)";
        
        try{

            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, userId);
            prepSt.setString(2, admin.getUsername());
            prepSt.setString(3, admin.getPassword());

            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        }
        return false;
    }
    
    private static boolean addAdminDataToRoles(String userId, AdminUser admin) {
        
        String query = "INSERT INTO roles VALUES(?,?)";
        
        try{

            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, userId);
            prepSt.setString(2, admin.getRole());

            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        }
        return false;
    }
    
    public static AdminUser retrieveAdminDataById(String userId) {
        String query = "SELECT profile_picture, profile_picture_path, first_name, middle_name, last_name, birthday, age, gender, email, phone_number, username, password FROM users "
                + "INNER JOIN authentication ON authentication.user_id = users.user_id WHERE users.user_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            prepSt.setString(1, userId);
            
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                Blob profilePictureImage = rs.getBlob("profile_picture");
//                String path = "D:\\MovieTicketingSystem\\adminstaffpics\\" + userId + ".jpg";
                String path = rs.getString("profile_picture_path");
                byte[] bytes = profilePictureImage.getBytes(1, (int)profilePictureImage.length());
                FileOutputStream fos = new FileOutputStream(path);
                fos.write(bytes);

                String firstName = rs.getString("first_name");
                String middleName = rs.getString("middle_name");
                String lastName = rs.getString("last_name");
                LocalDate birthday = rs.getDate("birthday").toLocalDate();
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phone_number");
                String username = rs.getString("username");
                String password = rs.getString("password");
                
                return new AdminUser(path, 
                            firstName, 
                            middleName, 
                            lastName, 
                            birthday, 
                            age, 
                            gender, 
                            email, 
                            phoneNumber, 
                            username, 
                            password); 
            }

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (IOException e) {
           e.printStackTrace();
        }
        return null;
    }
    
    public static boolean updateAdminDataById(AdminUser adminData) {
        
        if(!updateAdminDataInUsers(adminData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating admin data to users. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if(!updateAdminDataInAuthentication(adminData)) {
            JOptionPane.showMessageDialog(Helper.getCurrentFrame(), "Error updating admin data to users. Please try again later.", "Invalid Action", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    private static boolean updateAdminDataInUsers(AdminUser adminData) {
                String query = "UPDATE users "
                + "SET profile_picture = ?, "
                        + "profile_picture_path = ?, "
                + "first_name = ?, "
                + "middle_name = ?, "
                + "last_name = ?, "
                + "birthday = ?, "
                + "age = ?, "
                + "gender = ?, "
                + "email = ?, "
                + "phone_number = ? "
                        + "WHERE user_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);
            
            //Convert the photo into InputStream
            InputStream is = new FileInputStream(new File(adminData.getPicturePath()));
            
            Date birthDay = Date.valueOf(adminData.getBirthday());
            
            prepSt.setBlob(1, is);
            prepSt.setString(2, adminData.getPicturePath());
            prepSt.setString(3, adminData.getFirstName());
            prepSt.setString(4, adminData.getMiddleName());
            prepSt.setString(5, adminData.getLastName());
            prepSt.setDate(6, birthDay);
            prepSt.setInt(7, adminData.getAge());
            prepSt.setString(8, adminData.getGender());
            prepSt.setString(9, adminData.getEmail());
            prepSt.setString(10, adminData.getPhoneNumber());
            prepSt.setString(11, adminData.getUserId());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    private static boolean updateAdminDataInAuthentication(AdminUser adminData) {
                String query = "UPDATE authentication "
                + "SET username = ?, "
                + "password = ? "
                        + "WHERE user_id = ?";
        
        try {
            // Execute the query and get results
            PreparedStatement prepSt = connection.prepareStatement(query);       
            
            prepSt.setString(1, adminData.getUsername());
            prepSt.setString(2, adminData.getPassword());
            prepSt.setString(3, adminData.getUserId());
            
            prepSt.executeUpdate();
            
            return true;

        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace(); 
        } 
        return false;
    }
    
    
    
}
