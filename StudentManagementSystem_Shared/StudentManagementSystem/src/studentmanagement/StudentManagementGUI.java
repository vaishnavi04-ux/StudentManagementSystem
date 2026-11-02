 package studentmanagement;

import javax.swing.SwingUtilities;

public class StudentManagementGUI {

public static void main(String[] args) {

    SwingUtilities.invokeLater(() -> {
        new Dashboard();
    });

}

}
