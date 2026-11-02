package studentmanagement;

import java.util.List;

public interface StudentOperations {

void addStudent(Student student);

List<Student> viewAllStudents();

Student searchStudent(int studentId);

void updateStudent(Student student);

void deleteStudent(int studentId);

}
