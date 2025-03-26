import java.sql.*;

public class CourseDAO {
    public void addCourse(String courseName, int credits, String instructor) {
        String sql = "INSERT INTO Courses (courseName, credits, instructor) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, courseName);
            stmt.setInt(2, credits);
            stmt.setString(3, instructor);
            stmt.executeUpdate();
            System.out.println("Course added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void listCourses() {
        String sql = "SELECT * FROM Courses";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("courseId") +
                        ", Name: " + rs.getString("courseName") +
                        ", Credits: " + rs.getInt("credits") +
                        ", Instructor: " + rs.getString("instructor"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
