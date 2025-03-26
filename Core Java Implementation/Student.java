public class Student extends Person {
    private int studentID;

    public Student(int studentID, String name, String surname, String email) {
        super(name, email);
        this.studentID = studentID;
    }
    @Override
    public void displayDetails() {
        System.out.println("Student ID: " + studentID + ", Name" + name + ", Email" + email);
    }
}
