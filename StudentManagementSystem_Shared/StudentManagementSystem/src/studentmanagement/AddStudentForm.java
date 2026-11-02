package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class AddStudentForm extends JFrame {
	private static final long serialVersionUID = 1L;

public AddStudentForm() {

    setTitle("Add Student");
    setSize(450, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(6, 2, 10, 15));

    panel.setBorder(
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
    );

    // Student ID
    JLabel idLabel = new JLabel("Student ID:");
    JTextField idField = new JTextField();

    // Name
    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();

    // Age
    JLabel ageLabel = new JLabel("Age:");
    JTextField ageField = new JTextField();

    // Course
    JLabel courseLabel = new JLabel("Course:");
    JTextField courseField = new JTextField();

    // Email
    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField();

    // Buttons
    JButton saveButton = new JButton("Save Student");
    JButton cancelButton = new JButton("Cancel");

    panel.add(idLabel);
    panel.add(idField);

    panel.add(nameLabel);
    panel.add(nameField);

    panel.add(ageLabel);
    panel.add(ageField);

    panel.add(courseLabel);
    panel.add(courseField);

    panel.add(emailLabel);
    panel.add(emailField);

    panel.add(saveButton);
    panel.add(cancelButton);

    add(panel);

    // SAVE BUTTON
    saveButton.addActionListener(_ -> {
        try {

            String idText = idField.getText().trim();
            String name = nameField.getText().trim();
            String ageText = ageField.getText().trim();
            String course = courseField.getText().trim();
            String email = emailField.getText().trim();

            // Empty field validation
            if (idText.isEmpty() || name.isEmpty()
                    || ageText.isEmpty() || course.isEmpty()
                    || email.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Please fill all fields.");

                return;
            }

            // Convert values
            int id = Integer.parseInt(idText);
            int age = Integer.parseInt(ageText);

            // Positive number validation
            if (id <= 0 || age <= 0) {

                JOptionPane.showMessageDialog(this,
                        "Student ID and Age must be positive numbers.");

                return;
            }

            // Name validation
            if (!name.matches("[a-zA-Z ]+")) {

                JOptionPane.showMessageDialog(this,
                        "Name should contain only letters and spaces.");

                return;
            }

            // Email validation
            if (!email.matches(
                    "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

                JOptionPane.showMessageDialog(this,
                        "Please enter a valid email address.");

                return;
            }

            // Create Student object
            Student student = new Student(
                    id, name, age, course, email
            );

            // Save using existing backend
            StudentDAO dao = new StudentDAO();

            dao.addStudent(student);

            // Success message
            JOptionPane.showMessageDialog(this,
                    "Student added successfully!");

            dispose();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers for ID and Age.");

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());

        }

    });

    // CANCEL BUTTON
    cancelButton.addActionListener(_ -> dispose());
    setVisible(true);

}

}

