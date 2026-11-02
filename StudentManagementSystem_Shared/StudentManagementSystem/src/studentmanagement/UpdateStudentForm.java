package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class UpdateStudentForm extends JFrame {
	private static final long serialVersionUID = 1L;

public UpdateStudentForm() {

    setTitle("Update Student");
    setSize(500, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(6, 2, 10, 15));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JLabel idLabel = new JLabel("Student ID:");
    JTextField idField = new JTextField();

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();

    JLabel ageLabel = new JLabel("Age:");
    JTextField ageField = new JTextField();

    JLabel courseLabel = new JLabel("Course:");
    JTextField courseField = new JTextField();

    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField();

    JButton searchButton = new JButton("Load Student");
    JButton updateButton = new JButton("Update");
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
    panel.add(searchButton);
    panel.add(updateButton);

    add(panel, BorderLayout.CENTER);
    add(cancelButton, BorderLayout.SOUTH);

    searchButton.addActionListener(_ -> {

        try {
            int id = Integer.parseInt(idField.getText());

            StudentDAO dao = new StudentDAO();
            Student student = dao.searchStudent(id);

            if (student != null) {
                nameField.setText(student.getName());
                ageField.setText(String.valueOf(student.getAge()));
                courseField.setText(student.getCourse());
                emailField.setText(student.getEmail());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Student not found!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Enter a valid Student ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    });

    updateButton.addActionListener(_ -> {

        try {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText().trim();
            int age = Integer.parseInt(ageField.getText());
            String course = courseField.getText().trim();
            String email = emailField.getText().trim();

            if (name.isEmpty() || course.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please fill all fields.");
                return;
            }

            Student student = new Student(id, name, age, course, email);

            StudentDAO dao = new StudentDAO();
            dao.updateStudent(student);

            JOptionPane.showMessageDialog(this,
                    "Student updated successfully!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Enter valid numbers for ID and Age.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    });

    cancelButton.addActionListener(_ -> dispose());

    setVisible(true);
}

}
 
            
 





