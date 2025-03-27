import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public List<Enrollment> listEnrollments() {
        List<Enrollment> enrollments = new ArrayList<>();

        // Corrected SQL query
        String sql = "SELECT e.enrollmentID, s.studentID, s.name AS studentName, c.courseID, c.courseName, e.grade " +
                "FROM Enrollments e " +
                "JOIN Students s ON e.studentID = s.studentID " +
                "JOIN Courses c ON e.courseID = c.courseID";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                enrollments.add(new Enrollment(
                        rs.getInt("enrollmentID"),
                        new Student(rs.getInt("studentID"), rs.getString("studentName"), null, "email@example.com"),
                        new Course(rs.getInt("courseID"), rs.getString("courseName"), 0, "Unknown"),
                        rs.getString("grade")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enrollments;
    }

    public void enrollStudent(int studentID, int courseID, String grade) {
        String sql = "INSERT INTO Enrollments (studentID, courseID, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentID);
            stmt.setInt(2, courseID);
            stmt.setString(3, grade);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}