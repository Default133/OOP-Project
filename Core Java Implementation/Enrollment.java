public class Enrollment {
    private int enrollmentID;
    private Student student;
    private Course course;
    private String grade;

    public Enrollment(int enrollmentID, Student student, Course course, String grade) {
        this.enrollmentID = enrollmentID;
        this.student = student;
        this.course = course;
        this.grade = grade;
    }
    public void displayEnrollment(){
        System.out.println("Enrollment ID: " + enrollmentID + ", Student: " + student.name + ", Course: " + course.getCourseName() + ", Grade: " + grade);
    }
}
