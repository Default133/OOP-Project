public class Instructor extends Person {
    private String department;

    public Instructor(String name, String email, String department) {
        super(name, email);
        this.department = department;
    }
    @Override
    public void displayDetails() {
        System.out.println("Instructor: " + name + ", Email: " + email + ", Department: " + department);
    }
}
