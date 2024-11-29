package Database;

import Model.AuthenticationStatus;
import Model.Credential;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseManager {
    
    // Static database connection
    public static Connection connection;

    // Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "homecredit123";

    // Establishes a connection to the database.
    public static void connect() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            try {
                // Establish connection to the database
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cineBook", USER, PASSWORD);
            } catch (SQLException e) {
                // Handle SQL connection errors
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            // Handle errors in loading the JDBC driver
            e.printStackTrace();
        }
    }

}
