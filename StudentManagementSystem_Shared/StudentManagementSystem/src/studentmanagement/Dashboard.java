package studentmanagement;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
	private static final long serialVersionUID = 1L;

public Dashboard() {

    // Main Window
    setTitle("Student Management System");
    setSize(1100, 700);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());

    // ================= SIDEBAR =================

    JPanel sidebar = new JPanel();

    sidebar.setBackground(new Color(25, 35, 60));
    sidebar.setPreferredSize(new Dimension(240, 700));
    sidebar.setLayout(new GridLayout(7, 1, 10, 10));

    sidebar.setBorder(
            BorderFactory.createEmptyBorder(20, 15, 20, 15)
    );

    JLabel title = new JLabel("STUDENT SYSTEM");
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Arial", Font.BOLD, 20));

    JButton dashboardBtn = createButton("Dashboard");
    JButton addBtn = createButton("Add Student");
    JButton viewBtn = createButton("View Students");
    JButton searchBtn = createButton("Search Student");
    JButton updateBtn = createButton("Update Student");
    JButton deleteBtn = createButton("Delete Student");

    sidebar.add(title);
    sidebar.add(dashboardBtn);
    sidebar.add(addBtn);
    sidebar.add(viewBtn);
    sidebar.add(searchBtn);
    sidebar.add(updateBtn);
    sidebar.add(deleteBtn);

    // ================= MAIN CONTENT =================

    JPanel content = new JPanel(new BorderLayout());

    content.setBackground(new Color(245, 247, 250));

    content.setBorder(
            BorderFactory.createEmptyBorder(30, 35, 30, 35)
    );

    // Heading

    JLabel heading = new JLabel("Dashboard");

    heading.setFont(new Font("Arial", Font.BOLD, 30));
    heading.setForeground(new Color(25, 35, 60));

    JLabel welcome = new JLabel(
            "Welcome to your Student Management System"
    );

    welcome.setFont(new Font("Arial", Font.PLAIN, 16));

    JPanel topPanel = new JPanel(new GridLayout(2, 1, 5, 5));

    topPanel.setOpaque(false);

    topPanel.add(heading);
    topPanel.add(welcome);

    // ================= STUDENT COUNT CARD =================

    JPanel card = new JPanel(new GridLayout(2, 1, 5, 5));

    card.setBackground(Color.WHITE);

    card.setBorder(
            BorderFactory.createEmptyBorder(25, 25, 25, 25)
    );

    JLabel cardTitle = new JLabel("TOTAL STUDENTS");

    cardTitle.setFont(new Font("Arial", Font.BOLD, 16));

    JLabel countLabel = new JLabel("Loading...");

    countLabel.setFont(new Font("Arial", Font.BOLD, 35));

    countLabel.setForeground(new Color(35, 100, 180));

    card.add(cardTitle);
    card.add(countLabel);

    // Get total students from database

    try {

        StudentDAO dao = new StudentDAO();

        int total = dao.viewAllStudents().size();

        countLabel.setText(String.valueOf(total));

    } catch (Exception ex) {

        countLabel.setText("Unavailable");

    }

    // ================= DESCRIPTION =================

    JPanel centerPanel = new JPanel(new GridLayout(2, 1, 15, 15));

    centerPanel.setOpaque(false);

    centerPanel.add(card);

    JLabel description = new JLabel(
            "<html>"
            + "<div style='font-size:14px;'>"
            + "<h2>Manage Your Students Easily</h2>"
            + "Use the sidebar to manage student records.<br><br>"
            + "You can add, view, search, update and delete student details."
            + "</div>"
            + "</html>"
    );

    centerPanel.add(description);

    content.add(topPanel, BorderLayout.NORTH);

    content.add(centerPanel, BorderLayout.CENTER);

    // ================= BUTTON ACTIONS =================

    dashboardBtn.addActionListener(_ -> {
        dispose();
        new Dashboard();
    });

    addBtn.addActionListener(_ -> new AddStudentForm());

    viewBtn.addActionListener(_ -> new ViewStudentsForm());

    searchBtn.addActionListener(_ -> new SearchStudentForm());

    updateBtn.addActionListener(_ -> new UpdateStudentForm());

    deleteBtn.addActionListener(_ -> new DeleteStudentForm());
    // Add panels to window

    add(sidebar, BorderLayout.WEST);

    add(content, BorderLayout.CENTER);

    setVisible(true);
}

// ================= BUTTON DESIGN =================

private JButton createButton(String text) {

    JButton button = new JButton(text);

    button.setFont(new Font("Arial", Font.BOLD, 14));

    button.setForeground(Color.WHITE);

    button.setBackground(new Color(45, 60, 90));

    button.setFocusPainted(false);

    button.setBorderPainted(false);

    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    return button;
}

}
