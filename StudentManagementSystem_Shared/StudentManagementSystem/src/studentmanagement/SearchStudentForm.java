package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class SearchStudentForm extends JFrame {
	private static final long serialVersionUID = 1L;

public SearchStudentForm() {

    setTitle("Search Student");
    setSize(500, 350);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(5, 2, 10, 15));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JLabel idLabel = new JLabel("Enter Student ID:");
    JTextField idField = new JTextField();

    JLabel nameLabel = new JLabel("Name:");
    JTextField nameField = new JTextField();
    nameField.setEditable(false);

    JLabel ageLabel = new JLabel("Age:");
    JTextField ageField = new JTextField();
    ageField.setEditable(false);

    JLabel courseLabel = new JLabel("Course:");
    JTextField courseField = new JTextField();
    courseField.setEditable(false);

    JLabel emailLabel = new JLabel("Email:");
    JTextField emailField = new JTextField();
    emailField.setEditable(false);

    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Close");

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

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(searchButton);
    buttonPanel.add(cancelButton);

    add(panel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

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
                nameField.setText("");
                ageField.setText("");
                courseField.setText("");
                emailField.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter a valid Student ID.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + ex.getMessage());
        }
    });

    cancelButton.addActionListener(_ -> dispose());

    setVisible(true);
}

}
