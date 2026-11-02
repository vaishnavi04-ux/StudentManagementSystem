 package studentmanagement;

import java.util.List;
import java.util.Scanner;

public class Main {

public static void main(String[] args) {

    // Start the database checking thread
    DatabaseCheckThread dbThread = new DatabaseCheckThread();
    dbThread.start();

    // Wait until the database check finishes
    try {
        dbThread.join();
    } catch (InterruptedException e) {
        System.out.println("Database check interrupted.");
        Thread.currentThread().interrupt();
        return;
    }

    Scanner sc = new Scanner(System.in);
    StudentDAO dao = new StudentDAO();

    // Main menu
    while (true) {

        System.out.println("\n==================================");
        System.out.println("     STUDENT MANAGEMENT SYSTEM");
        System.out.println("==================================");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. Update Student");
        System.out.println("5. Delete Student");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");

        try {
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {

                // Add Student
                case 1:
                    try {
                        System.out.print("Enter Student ID: ");
                        int id = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter Name: ");
                        String name = sc.nextLine().trim();

                        System.out.print("Enter Age: ");
                        int age = Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter Course: ");
                        String course = sc.nextLine().trim();

                        System.out.print("Enter Email: ");
                        String email = sc.nextLine().trim();

                        if (name.isEmpty() || course.isEmpty()
                                || email.isEmpty() || age <= 0 || id <= 0) {
                            System.out.println("Please enter valid student details.");
                            break;
                        }

                        Student student =
                                new Student(id, name, age, course, email);

                        dao.addStudent(student);

                    } catch (NumberFormatException e) {
                        System.out.println("ID and age must be numbers.");
                    }
                    break;

                // View All Students
                case 2:
                    List<Student> students = dao.viewAllStudents();

                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("\n--- Student Records ---");

                        for (Student student : students) {
                            student.displayDetails();
                            System.out.println("------------------------");
                        }
                    }
                    break;

                // Search Student
                case 3:
                    try {
                        System.out.print("Enter Student ID to search: ");
                        int searchId =
                                Integer.parseInt(sc.nextLine().trim());

                        Student found = dao.searchStudent(searchId);

                        if (found != null) {
                            System.out.println("\nStudent found:");
                            found.displayDetails();
                        } else {
                            System.out.println("Student not found.");
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric ID.");
                    }
                    break;

                // Update Student
                case 4:
                    try {
                        System.out.print("Enter Student ID to update: ");
                        int updateId =
                                Integer.parseInt(sc.nextLine().trim());

                        Student existing = dao.searchStudent(updateId);

                        if (existing == null) {
                            System.out.println("Student not found.");
                            break;
                        }

                        System.out.println("Enter the new details:");

                        System.out.print("Enter Name: ");
                        String newName = sc.nextLine().trim();

                        System.out.print("Enter Age: ");
                        int newAge =
                                Integer.parseInt(sc.nextLine().trim());

                        System.out.print("Enter Course: ");
                        String newCourse = sc.nextLine().trim();

                        System.out.print("Enter Email: ");
                        String newEmail = sc.nextLine().trim();

                        if (newName.isEmpty() || newCourse.isEmpty()
                                || newEmail.isEmpty() || newAge <= 0) {
                            System.out.println("Please enter valid details.");
                            break;
                        }

                        Student updated = new Student(
                                updateId,
                                newName,
                                newAge,
                                newCourse,
                                newEmail
                        );

                        dao.updateStudent(updated);

                    } catch (NumberFormatException e) {
                        System.out.println("ID and age must be numbers.");
                    }
                    break;

                // Delete Student
                case 5:
                    try {
                        System.out.print("Enter Student ID to delete: ");
                        int deleteId =
                                Integer.parseInt(sc.nextLine().trim());

                        dao.deleteStudent(deleteId);

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid numeric ID.");
                    }
                    break;

                // Exit
                case 6:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please select 1-6.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Please enter a number from 1 to 6.");
        }
    }
}

}

