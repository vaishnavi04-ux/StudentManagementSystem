package studentmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnection {

public static void main(String[] args) {

    String url = "jdbc:mysql://localhost:3306/student_management";
    String user = "root";
    String password = "Nila@Java2026#Db";

    try {
        Connection con = DriverManager.getConnection(url, user, password);

        System.out.println("Connection Done");

        con.close();

    } catch (SQLException e) {
        System.out.println("Connection Failed!");
        e.printStackTrace();
    }
}

}
