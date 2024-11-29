package Database;

import Model.AuthenticationStatus;
import Model.Credential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticateDatabaseManager extends DatabaseManager{
    
    public static AuthenticationStatus verifyAuthentication(Credential userCredential) {
        
        connect();
        
        //Either a user_id if success, otherwise return 0
        String validationReturn = isUserPasswordValid(userCredential);
        if(validationReturn.equals("0")) {
            return new AuthenticationStatus(false, "","");
        } 
        
        String userRole = getRole(validationReturn);
        
        return new AuthenticationStatus(true, validationReturn, userRole);
         
    }
    
    //Returns user_id if success, otherwise return 0
    private static String isUserPasswordValid(Credential userCredential) {
        String checkUsernameQuery = "SELECT user_id, password FROM authentication WHERE username = ? LIMIT 1";
        
        try (PreparedStatement prepSt = connection.prepareStatement(checkUsernameQuery)) {
            // Execute the query and get results
            prepSt.setString(1, userCredential.getUsername());
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String retrievedPassword = rs.getString("password");
                
                if (!retrievedPassword.equals(userCredential.getPassword())) {
                    return "0";
                } else {
                    String retrievedUserId = rs.getString("user_id");
                    return retrievedUserId;
                }
            }
            
            return "0";
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace();
        }
        return "0";
    }
    
    private static String getRole(String userId) {
        String getUserIDQuery = "SELECT role FROM roles WHERE user_id = ? LIMIT 1";
        
        try (PreparedStatement prepSt = connection.prepareStatement(getUserIDQuery)) {
            // Execute the query and get results
            prepSt.setString(1, userId);
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String retrievedUserId = rs.getString("role");
                
                return retrievedUserId;
            }
            
            return "0";
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace();
        }
        
        return "0";
    }
    
}
