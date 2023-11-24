import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String user = "root";
        String password = "hello";

        // JDBC variables for opening, closing and managing connection
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // The stored procedure takes an employee ID as input and returns employee details
            String storedProcedure = "{call getEmployeeDetails(?, ?, ?, ?)}";

            try (CallableStatement callableStatement = connection.prepareCall(storedProcedure)) {
                // Set input parameter (employee ID)
                int id = 1;
                callableStatement.setInt(1, id);

                // Register OUT parameters
                callableStatement.registerOutParameter(2, Types.VARCHAR);  // Employee Name
                callableStatement.registerOutParameter(3, Types.VARCHAR);  // Employee Job Title
                callableStatement.registerOutParameter(4, Types.DOUBLE);   // Employee Salary

                // Execute the stored procedure
                callableStatement.execute();

                // Retrieve OUT parameters
                String name = callableStatement.getString(2);
                String jobTitle = callableStatement.getString(3);
                double salary = callableStatement.getDouble(4);

                // Display the retrieved employee details
                System.out.println("Employee ID: " + id);
                System.out.println("Employee Name: " + name);
                System.out.println("Employee Job Title: " + jobTitle);
                System.out.println("Employee Salary: " + salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
