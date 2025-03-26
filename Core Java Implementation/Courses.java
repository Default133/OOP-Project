public class Course {
    private int courseId;
    private String courseName;
    private int credits;
    private String instructor;
    public Course(int courseId, String courseName, int credits, String instructor) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.instructor = instructor;


    }
    public String getCourseName() {
        return courseName;
    }
    public void displayCourse() {
        System.out.println("Course ID: " + courseId + ", Name: " + courseName + ", Credits: " + credits + ", Instructor: " + instructor);
    }
}
