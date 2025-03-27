import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class StudentFrame extends JFrame {
    private JTextField nameField, emailField;
    private DefaultTableModel tableModel;
    private JTable studentTable;
    private StudentDAO studentDAO = new StudentDAO();

    public StudentFrame() {
        setTitle("Manage Students");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email:"));
        emailField = new JTextField();
        formPanel.add(emailField);

        JButton addButton = new JButton("Add Student");
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);

        // Table to display students
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email"}, 0);
        studentTable = new JTable(tableModel);
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        loadStudents();

        addButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            if (!name.isEmpty() && !email.isEmpty()) {
                try {
                    studentDAO.addStudent(name, email);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                loadStudents();
            }
        });
    }

    private void loadStudents() {
        tableModel.setRowCount(0);
        studentDAO.listStudents().forEach(s -> tableModel.addRow(new Object[]{s.getStudentID(), s.getName(), s.getEmail()}));
    }
}