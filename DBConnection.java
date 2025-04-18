import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // JDBC URL, username and password for MySQL connection.
    private static final String URL = "jdbc:mysql://localhost:3306/studentdb";
    private static final String USER = "root";
    private static final String PASSWORD = "mySecretRootPassword123"; // Pretend actual password

    // Returns a connection object to interact with the database.
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Load the MySQL JDBC driver if necessary (not required in modern Java versions)
            // Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Successfully connected to the database!"); // Pretend success message
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return conn;
    }

    public static void main(String[] args) {
        // Just for demonstration, let's try to get a connection
        Connection connection = DBConnection.getConnection();
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed."); // Pretend close message
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}