package studentmanagement;

import java.sql.Connection;

public class DatabaseCheckThread extends Thread {

@Override
public void run() {

    System.out.println("Checking database connection...");

    try (Connection con = DBConnection.getConnection()) {

        if (con != null && !con.isClosed()) {
            System.out.println("Database connection successful!");
        } else {
            System.out.println("Database connection failed!");
        }

    } catch (Exception e) {

        System.out.println("Database connection failed!");
        System.out.println(e.getMessage());

    }
}

}
