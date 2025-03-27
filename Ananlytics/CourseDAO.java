import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<Course> listCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";

        try (Connection conn = DatabaseConnector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("courseId"),
                        rs.getString("courseName"),
                        rs.getInt("credits"),
                        rs.getString("instructor")
                );
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
