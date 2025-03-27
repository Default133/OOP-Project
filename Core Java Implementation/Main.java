import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Test Database Connection
        Connection conn = DatabaseConnector.connect();
        if (conn != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        // Test CRUD Operations
        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        // Add Students
        studentDAO.addStudent("John Doe", "johndoe@example.com");
        studentDAO.addStudent("Jane Smith", "janesmith@example.com");

        // Add Courses
        courseDAO.addCourse("Software Engineering", 3, "Dr. Emily");
        courseDAO.addCourse("Networking", 3, "Dr. Alan");

        // Enroll Students
        enrollmentDAO.enrollStudent(1, 1, "A");
        enrollmentDAO.enrollStudent(1, 2, "B");
        enrollmentDAO.enrollStudent(2, 1, "C");

        // Display Data
        System.out.println("\n--- Students ---");
        studentDAO.listStudents();

        System.out.println("\n--- Courses ---");
        courseDAO.listCourses();

        System.out.println("\n--- Enrollments ---");
        enrollmentDAO.listEnrollments();
    }
}