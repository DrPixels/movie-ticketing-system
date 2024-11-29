package Model;

public class AuthenticationStatus {
  
    private boolean status;
    private String userId;
    private String role;
    
    public AuthenticationStatus(boolean status, String userId, String role) {
        this.status = status;
        this.userId = userId;
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
