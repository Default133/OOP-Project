import java.sql.Connection;
import java.sql.SQLException;

class Main {
    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnector.connect();
        if (conn != null) {
            System.out.println("Database connected successfully!");
        } else {
            System.out.println("Failed to connect to the database.");
        }

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

        // Add Students
        studentDAO.addStudent("Jens Annan", "jensAnnan@gmail.com");
        studentDAO.addStudent("Cole Maina", "coleMaina@gmail.com");

        // Display Data
        System.out.println("\n--- Students ---");
        for (Student student : studentDAO.listStudents()) {
            System.out.println(student);
        }
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

