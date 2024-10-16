

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class config {
    // Method to connect to the SQLite database
    public static Connection connectDB() {
        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC"); // Load SQLite JDBC driver
            con = DriverManager.getConnection("jdbc:sqlite:project.db"); // Connect to the database
            System.out.println("Connection Successful");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
        return con;
    }

    // Method to add an Instructor to the database
    public void addInstructor(Instructor instructor) {
        String sql = "INSERT INTO instructors (name, email, department, phone_number, hire_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getEmail());
            pstmt.setString(3, instructor.getDepartment());
            pstmt.setString(4, instructor.getPhoneNumber());
            pstmt.setDate(5, instructor.getHireDate());

            pstmt.executeUpdate();
            System.out.println("Instructor added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding instructor: " + e.getMessage());
        }
    }

    // Method to view all instructors
    public void viewInstructors() {
        String sqlQuery = "SELECT * FROM instructors";
        String[] columnHeaders = {"ID", "Name", "Email", "Department", "Phone Number", "Hire Date"};
        String[] columnNames = {"instructor_id", "name", "email", "department", "phone_number", "hire_date"};

        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
             ResultSet rs = pstmt.executeQuery()) {

            // Print the headers dynamically
            StringBuilder headerLine = new StringBuilder();
            headerLine.append("-----------------------------------------------------------------------------\n| ");
            for (String header : columnHeaders) {
                headerLine.append(String.format("%-15s | ", header)); // Adjust formatting as needed
            }
            headerLine.append("\n-----------------------------------------------------------------------------");

            System.out.println(headerLine.toString());

            // Print the rows dynamically
            while (rs.next()) {
                StringBuilder row = new StringBuilder("| ");
                for (String colName : columnNames) {
                    String value = rs.getString(colName);
                    row.append(String.format("%-15s | ", value != null ? value : "")); // Adjust formatting
                }
                System.out.println(row.toString());
            }
            System.out.println("-----------------------------------------------------------------------------");

        } catch (SQLException e) {
            System.out.println("Error retrieving instructors: " + e.getMessage());
        }
    }

    // Method to update an Instructor
    public void updateInstructor(Instructor instructor) {
        String sql = "UPDATE instructors SET name = ?, email = ?, department = ?, phone_number = ?, hire_date = ? WHERE instructor_id = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters for the query
            pstmt.setString(1, instructor.getName());
            pstmt.setString(2, instructor.getEmail());
            pstmt.setString(3, instructor.getDepartment());
            pstmt.setString(4, instructor.getPhoneNumber());
            pstmt.setDate(5, instructor.getHireDate());
            pstmt.setInt(6, instructor.getInstructorId());

            pstmt.executeUpdate();
            System.out.println("Instructor updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating instructor: " + e.getMessage());
        }
    }

    // Method to delete an Instructor
    public void deleteInstructor(int instructorId) {
        String sql = "DELETE FROM instructors WHERE instructor_id = ?";
        try (Connection conn = config.connectDB();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set the instructor ID to delete
            pstmt.setInt(1, instructorId);
            pstmt.executeUpdate();
            System.out.println("Instructor deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting instructor: " + e.getMessage());
        }
    }
}
