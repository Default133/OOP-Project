import java.util.List;
public class Statistics implements Analytics{
    private List<Enrollment> enrollments;
    public Statistics(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
    @Override
    public double calculateAverageGrade() {
        int totalPoints = 0, count = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade().equals("A"))
                totalPoints += 4;
            if (e.getGrade().equals("B"))
                totalPoints += 3;
            if (e.getGrade().equals("C"))
                totalPoints += 2;
            count++;


        }
        return count == 0 ? 0 : (double) totalPoints / count;

    }}