package Model;

public class AuthenticationStatus {
  
    private boolean status;
    private String employeeId;
    private String role;
    
    public AuthenticationStatus(boolean status, String employeeId, String role) {
        this.status = status;
        this.employeeId = employeeId;
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
