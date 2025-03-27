import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnalyticsFrame extends JFrame {
    public AnalyticsFrame() {
        setTitle("Analytics");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        // Create and add the charts
        add(new ChartPanel(createEnrollmentChart()));
        add(new ChartPanel(createGradeDistributionChart()));
    }

    // Bar Chart: Course Enrollments
    private JFreeChart createEnrollmentChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT c.courseName, COUNT(e.studentID) AS total " +
                "FROM Enrollments e JOIN Courses c ON e.courseId = c.courseId " +
                "GROUP BY c.courseName";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dataset.addValue(rs.getInt("total"), "Enrollments", rs.getString("courseName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ChartFactory.createBarChart(
                "Student Enrollment per Course",
                "Course Name",
                "Number of Students",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );
    }

    // Pie Chart: Grade Distribution
    private JFreeChart createGradeDistributionChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        String query = "SELECT grade, COUNT(*) AS count FROM Enrollments GROUP BY grade";

        try (Connection conn = DatabaseConnector.connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                dataset.setValue(rs.getString("grade"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Grade Distribution",
                dataset,
                true, true, false
        );

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSimpleLabels(true);
        return chart;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AnalyticsFrame().setVisible(true));
    }
}
