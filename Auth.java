import java.io.*;
import java.util.*;

public class Auth {
    private static final String CUSTOMER_FILE = "customer.txt";
    private static final String ADMIN_FILE = "admin.txt";

    public static boolean registerCustomer(Customer customer) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(CUSTOMER_FILE, true))) {
            bw.write(customer.getUsername() + "," + customer.getPassword() + "," +
                     customer.getCustomerId() + "," + customer.getName() + "," +
                     customer.getAddress());
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean registerAdmin(Admin admin) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ADMIN_FILE, true))) {
            bw.write(admin.getUsername() + "," + admin.getPassword());
            bw.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static User login(String username, String password) {
        try {
            // Check admin file
            BufferedReader adminReader = new BufferedReader(new FileReader(ADMIN_FILE));
            String line;
            while ((line = adminReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    adminReader.close();
                    return new Admin(parts[0], parts[1]);
                }
            }
            adminReader.close();

            // Check customer file
            BufferedReader custReader = new BufferedReader(new FileReader(CUSTOMER_FILE));
            while ((line = custReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username) && parts[1].equals(password)) {
                    custReader.close();
                    return new Customer(parts[0], parts[1], parts[2], parts[3], parts[4]);
                }
            }
            custReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Invalid login
    }
}
