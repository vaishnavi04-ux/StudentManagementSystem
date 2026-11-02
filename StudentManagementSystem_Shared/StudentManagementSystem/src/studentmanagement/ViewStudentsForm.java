package studentmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewStudentsForm extends JFrame {
	private static final long serialVersionUID = 1L;

public ViewStudentsForm() {

    setTitle("View Students");
    setSize(800, 450);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    String[] columns = {
        "Student ID", "Name", "Age", "Course", "Email"
    };

    DefaultTableModel model = new DefaultTableModel(columns, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    JTable table = new JTable(model);
    table.setRowHeight(25);

    try {
        StudentDAO dao = new StudentDAO();
        List<Student> students = dao.viewAllStudents();

        for (Student student : students) {
            model.addRow(new Object[]{
                student.getStudentId(),
                student.getName(),
                student.getAge(),
                student.getCourse(),
                student.getEmail()
            });
        }

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Error loading students: " + ex.getMessage());
    }

    JLabel heading = new JLabel("All Student Records");
    heading.setFont(new Font("Arial", Font.BOLD, 22));
    heading.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    add(heading, BorderLayout.NORTH);
    add(new JScrollPane(table), BorderLayout.CENTER);

    setVisible(true);
}

}

