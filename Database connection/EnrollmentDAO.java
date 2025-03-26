import java.sql.*;

public class EnrollmentDAO {
    public void enrollStudent(int studentId, int courseId, String grade) {
        String sql = "INSERT INTO Enrollments (studentID, courseId, grade) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            stmt.setInt(2, courseId);
            stmt.setString(3, grade);
            stmt.executeUpdate();
            System.out.println("Student enrolled successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listEnrollments() {
        String sql = "SELECT e.enrollmentID, s.name, c.courseName, e.grade " +
                "FROM Enrollments e " +
                "JOIN Students s ON e.studentID = s.studentID " +
                "JOIN Courses c ON e.courseId = c.courseId";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("Enrollment ID: " + rs.getInt("enrollmentID") +
                        ", Student: " + rs.getString("name") +
                        ", Course: " + rs.getString("courseName") +
                        ", Grade: " + rs.getString("grade"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
