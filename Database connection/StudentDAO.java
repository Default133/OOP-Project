import java.sql.*;
public class StudentDAO {
    public void addStudent(String name, String email) throws SQLException {
        String sql = "insert into student (name, email) values (?, ?)";
        try(Connection conn = DatabaseConnector.connect();
        PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Student added successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void listStudents(){
        String sql = "SELECT * FROM Students";
        try(Connection conn = DatabaseConnector.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getString("studentID") + rs.getString("Name") + ", Email: " + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
