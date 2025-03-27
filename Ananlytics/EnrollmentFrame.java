import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EnrollmentFrame extends JFrame {
    private JTextField studentIDField, courseIDField, gradeField;
    private DefaultTableModel tableModel;
    private JTable enrollmentTable;
    private EnrollmentDAO enrollmentDAO = new EnrollmentDAO();

    public EnrollmentFrame() {
        setTitle("Manage Enrollments");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Student ID:"));
        studentIDField = new JTextField();
        formPanel.add(studentIDField);

        formPanel.add(new JLabel("Course ID:"));
        courseIDField = new JTextField();
        formPanel.add(courseIDField);

        formPanel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        formPanel.add(gradeField);

        JButton enrollButton = new JButton("Enroll Student");
        formPanel.add(enrollButton);

        add(formPanel, BorderLayout.NORTH);

        // Table to display enrollments
        tableModel = new DefaultTableModel(new String[]{"ID", "Student", "Course", "Grade"}, 0);
        enrollmentTable = new JTable(tableModel);
        add(new JScrollPane(enrollmentTable), BorderLayout.CENTER);

        loadEnrollments();

        enrollButton.addActionListener(e -> {
            int studentID = Integer.parseInt(studentIDField.getText());
            int courseID = Integer.parseInt(courseIDField.getText());
            String grade = gradeField.getText();
            if (grade.length() == 1) {
                enrollmentDAO.enrollStudent(studentID, courseID, grade);
                loadEnrollments();
            }
        });
    }

    private void loadEnrollments() {
        tableModel.setRowCount(0);
        enrollmentDAO.listEnrollments().forEach(e -> tableModel.addRow(new Object[]{e.getEnrollmentID(), e.getStudentName(), e.getCourseName(), e.getGrade()}));
    }
}