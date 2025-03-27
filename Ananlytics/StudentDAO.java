import java.sql.*;
import java.util.ArrayList;
import java.util.List;
class StudentDAO {
    public void addStudent(String name, String email) throws SQLException {
        String sql = "insert into students (name, email) values (?, ?)";
        try (Connection conn = DatabaseConnector.connect()) {
            if (conn == null) {
                System.err.println("Failed to connect to the database.");
                return;
            }
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.executeUpdate();
                System.out.println("Student added successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> listStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection conn = DatabaseConnector.connect()) {
            if (conn == null) {
                System.err.println("Failed to connect to the database.");
                return students;
            }
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    int studentID = rs.getInt("studentID");
                    String name = rs.getString("Name");
                    String email = rs.getString("email");
                    students.add(new Student(studentID, name, null, email));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }
}


