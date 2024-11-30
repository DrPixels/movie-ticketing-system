package Database;

import Model.AuthenticationStatus;
import Model.Credential;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticateDatabaseManager extends DatabaseManager{
    
    public static AuthenticationStatus verifyAuthentication(Credential userCredential) {
        
        connect();
        
        //Either a employee_id if success, otherwise return 0
        String validationReturn = isUserPasswordValid(userCredential);
        if(validationReturn.equals("0")) {
            return new AuthenticationStatus(false, "","");
        } 
        
        String userRole = getRole(validationReturn);
        
        return new AuthenticationStatus(true, validationReturn, userRole);
         
    }
    
    //Returns employee_id if success, otherwise return 0
    private static String isUserPasswordValid(Credential userCredential) {
        String checkUsernameQuery = "SELECT employee_id, password FROM authentication WHERE username = ? LIMIT 1";
        
        try (PreparedStatement prepSt = connection.prepareStatement(checkUsernameQuery)) {
            // Execute the query and get results
            prepSt.setString(1, userCredential.getUsername());
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String retrievedPassword = rs.getString("password");
                
                if (!retrievedPassword.equals(userCredential.getPassword())) {
                    return "0";
                } else {
                    String retrievedEmployeeId = rs.getString("employee_id");
                    return retrievedEmployeeId;
                }
            }
            
            return "0";
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace();
        }
        return "0";
    }
    
    private static String getRole(String employeeId) {
        String getEmployeeIdQuery = "SELECT role FROM roles WHERE employee_id = ? LIMIT 1";
        
        try (PreparedStatement prepSt = connection.prepareStatement(getEmployeeIdQuery)) {
            // Execute the query and get results
            prepSt.setString(1, employeeId);
            ResultSet rs = prepSt.executeQuery();
            
            if(rs.next()) {
                String retrievedEmployeeId = rs.getString("role");
                
                return retrievedEmployeeId;
            }
            
            return "0";
            
        } catch (SQLException e) {
            // Handle SQL errors during query execution
            e.printStackTrace();
        }
        
        return "0";
    }
    
}
