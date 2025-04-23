import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class mainMenu {
    public static void main(String[] args) {

        try (Connection conn = DatabaseConnection.getConnection();
             Scanner scanner = new Scanner(System.in)) {

            int choice;

            do {
                System.out.println("\n=====  BSIT 2NDSTUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Student");
                System.out.println("2. Retrieve Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Show All Students");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                InsertStudent insert = new InsertStudent();
                RetrieveStudent ret = new RetrieveStudent();
                UpdateStudent upd = new UpdateStudent();
                DeleteStudent del = new DeleteStudent();

                switch (choice) {
                    case 1:
                        insert.add(conn, scanner);
                        break;
                    case 2:
                        ret.retrieve(conn, scanner);
                        break;
                    case 3:
                        upd.update(conn, scanner);
                        break;
                    case 4:
                        del.delete(conn, scanner);
                        break;
                    case 5:
                        ret.retrieveAll(conn); 
                        break;
                    case 6:
                        System.out.println("Thank you. Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } while (choice != 6);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}