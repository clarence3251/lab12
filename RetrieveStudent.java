import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RetrieveStudent {

    public void retrieve(Connection conn, Scanner scanner){
        try {

            String choice;

            do {
                System.out.print("Enter student ID to retrieve: ");
                int studentId = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                String sql = "SELECT name, course FROM students WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, studentId);

                ResultSet resultSet = stmt.executeQuery();

                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String course = resultSet.getString("course");

                    System.out.println("Student Found:");
                    System.out.println("Name: " + name);
                    System.out.println("Course: " + course);
                } else {
                    System.out.println("No student found with ID " + studentId);
                }

                System.out.print("Do you want to retrieve another record? (yes/no): ");
                choice = scanner.nextLine().trim().toLowerCase();

            } while (choice.equals("yes"));

            System.out.println("Finished retrieving student records.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void retrieveAll(Connection conn) {
        String sql = "SELECT * FROM students";

        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n===== All Student Records =====");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String course = rs.getString("course");

                System.out.println("ID: " + id + " | Name: " + name + " | Course: " + course);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}