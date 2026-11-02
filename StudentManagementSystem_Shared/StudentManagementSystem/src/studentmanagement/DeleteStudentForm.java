package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class DeleteStudentForm extends JFrame {
	private static final long serialVersionUID = 1L;

public DeleteStudentForm() {

    setTitle("Delete Student");
    setSize(450, 250);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(2, 2, 10, 15));
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

    JLabel idLabel = new JLabel("Enter Student ID:");
    JTextField idField = new JTextField();

    JButton deleteButton = new JButton("Delete Student");
    JButton cancelButton = new JButton("Cancel");

    panel.add(idLabel);
    panel.add(idField);
    panel.add(deleteButton);
    panel.add(cancelButton);

    add(panel);

    deleteButton.addActionListener(_ -> {

        try {
            int id = Integer.parseInt(idField.getText());

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete Student ID " + id + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {

                StudentDAO dao = new StudentDAO();
                dao.deleteStudent(id);

                JOptionPane.showMessageDialog(this,
                        "Delete operation completed. Check the student records.");

                dispose();
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


