import java.io.*;

public class CustomerManager {
    private static final String CUSTOMER_FILE = "customer.txt";

    public static void viewAllCustomers() {
        try (BufferedReader br = new BufferedReader(new FileReader(CUSTOMER_FILE))) {
            String line;
            System.out.println("\n--- Customer List ---");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println("ID: " + parts[2] + ", Name: " + parts[3] + ", Address: " + parts[4]);
            }
        } catch (IOException e) {
            System.out.println("Error reading customer list.");
        }
    }
}
