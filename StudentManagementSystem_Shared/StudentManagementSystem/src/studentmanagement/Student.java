 package studentmanagement;

public class Student extends Person {

private int studentId;
private String course;

public Student(int studentId, String name, int age, String course, String email) {
    super(name, age, email);
    this.studentId = studentId;
    this.course = course;
}

public int getStudentId() {
    return studentId;
}

public void setStudentId(int studentId) {
    this.studentId = studentId;
}

public String getCourse() {
    return course;
}

public void setCourse(String course) {
    this.course = course;
}

@Override
public void displayDetails() {
    System.out.println("Student ID: " + studentId);
    System.out.println("Name: " + getName());
    System.out.println("Age: " + getAge());
    System.out.println("Course: " + course);
    System.out.println("Email: " + getEmail());
}

}

