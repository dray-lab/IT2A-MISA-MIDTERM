import java.sql.Date;
import java.util.Scanner;

public class InstructorManagementSystem {

    public static void main(String[] args) {
        config config = new config();
        Scanner scanner = new Scanner(System.in);
        int option;

        while (true) {
            // Display menu
            System.out.println("\nInstructor Management System");
            System.out.println("1. Add Instructor");
            System.out.println("2. View Instructors");
            System.out.println("3. Update Instructor");
            System.out.println("4. Delete Instructor");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (option) {
                case 1: // Add Instructor
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Department: ");
                    String department = scanner.nextLine();

                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    System.out.print("Enter Hire Date (YYYY-MM-DD): ");
                    String hireDateString = scanner.nextLine();
                    Date hireDate = Date.valueOf(hireDateString);  // Convert String to SQL Date

                    Instructor newInstructor = new Instructor(0, name, email, department, phoneNumber, hireDate);
                    config.addInstructor(newInstructor);
                    break;

                case 2: // View Instructors
                    config.viewInstructors();
                    break;

                case 3: // Update Instructor
                    System.out.print("Enter Instructor ID to Update: ");
                    int updateId = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    System.out.print("Enter Updated Name: ");
                    String updateName = scanner.nextLine();

                    System.out.print("Enter Updated Email: ");
                    String updateEmail = scanner.nextLine();

                    System.out.print("Enter Updated Department: ");
                    String updateDepartment = scanner.nextLine();

                    System.out.print("Enter Updated Phone Number: ");
                    String updatePhoneNumber = scanner.nextLine();

                    System.out.print("Enter Updated Hire Date (YYYY-MM-DD): ");
                    String updateHireDateString = scanner.nextLine();
                    Date updateHireDate = Date.valueOf(updateHireDateString);

                    Instructor updatedInstructor = new Instructor(updateId, updateName, updateEmail, updateDepartment, updatePhoneNumber, updateHireDate);
                    config.updateInstructor(updatedInstructor);
                    break;

                case 4: // Delete Instructor
                    System.out.print("Enter Instructor ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    config.deleteInstructor(deleteId);
                    break;

                case 5: // Exit
                    System.out.println("Exiting the program...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please choose again.");
                    break;
            }
        }
    }
}
