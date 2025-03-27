import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("University Course Management System");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JButton studentButton = new JButton("Manage Students");
        JButton courseButton = new JButton("Manage Courses");
        JButton enrollmentButton = new JButton("Manage Enrollments");

        studentButton.addActionListener(e -> new StudentFrame().setVisible(true));
        courseButton.addActionListener(e -> new CourseFrame().setVisible(true));
        enrollmentButton.addActionListener(e -> new EnrollmentFrame().setVisible(true));

        add(studentButton);
        add(courseButton);
        add(enrollmentButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}