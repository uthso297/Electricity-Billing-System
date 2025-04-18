import java.io.*;
import java.util.*;

public class Billing {
    private static final String READING_FILE = "meter_readings.txt";
    private static final String BILL_FILE = "bills.txt";
    private static final double RATE_PER_UNIT = 5.0;

    public static void generateBill(Scanner sc) {
        try {
            System.out.print("Enter Customer ID: ");
            String cid = sc.nextLine();
            System.out.print("Enter Month (e.g., 2025-04): ");
            String month = sc.nextLine();

            BufferedReader br = new BufferedReader(new FileReader(READING_FILE));
            String line;
            double units = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(cid) && parts[1].equals(month)) {
                    units = Double.parseDouble(parts[2]);
                    break;
                }
            }
            br.close();

            if (units == 0) {
                System.out.println("No reading found for the customer in given month.");
                return;
            }

            double amount = units * RATE_PER_UNIT;

            BufferedWriter bw = new BufferedWriter(new FileWriter(BILL_FILE, true));
            bw.write(cid + "," + month + "," + units + "," + amount + ",unpaid");
            bw.newLine();
            bw.close();

            System.out.println("Bill generated: $" + amount);
        } catch (IOException e) {
            System.out.println("Error generating bill.");
        }
    }

    public static void viewBill(String customerId) {
        try (BufferedReader br = new BufferedReader(new FileReader(BILL_FILE))) {
            String line;
            System.out.println("\n--- Bills ---");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(customerId)) {
                    System.out.println("Month: " + parts[1] + ", Units: " + parts[2] +
                                       ", Amount: $" + parts[3] + ", Status: " + parts[4]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading bills.");
        }
    }
}
