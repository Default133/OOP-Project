import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CourseFrame extends JFrame {
    private JTextField nameField, creditsField, instructorField;
    private DefaultTableModel tableModel;
    private JTable courseTable;
    private CourseDAO courseDAO = new CourseDAO();

    public CourseFrame() {
        setTitle("Manage Courses");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(4, 2));
        formPanel.add(new JLabel("Course Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Credits:"));
        creditsField = new JTextField();
        formPanel.add(creditsField);

        formPanel.add(new JLabel("Instructor:"));
        instructorField = new JTextField();
        formPanel.add(instructorField);

        JButton addButton = new JButton("Add Course");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        // Table to display courses
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Credits", "Instructor"}, 0);
        courseTable = new JTable(tableModel);
        add(new JScrollPane(courseTable), BorderLayout.CENTER);

        loadCourses();

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            int credits = Integer.parseInt(creditsField.getText());
            String instructor = instructorField.getText();
            if (!name.isEmpty() && credits > 0 && !instructor.isEmpty()) {
                courseDAO.addCourse(name, credits, instructor);
                loadCourses();
            }
        });
    }

    private void loadCourses() {
        tableModel.setRowCount(0);
        courseDAO.listCourses().forEach(c -> tableModel.addRow(new Object[]{c.getCourseID(), c.getCourseName(), c.getCredits(), c.getInstructor()}));
    }
}