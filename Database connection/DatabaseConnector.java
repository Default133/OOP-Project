import java.sql.*;
public class DatabaseConnector {
    private static final String URL = "jdbc:mariadb://localhost:3306/UCMS";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try{
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            return null;
        }
    }
}
