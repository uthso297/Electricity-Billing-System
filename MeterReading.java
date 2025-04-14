import java.io.*;
import java.util.*;

public class MeterReading {
    private static final String READING_FILE = "meter_readings.txt";

    public static void recordReading(Scanner sc) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(READING_FILE, true))) {
            System.out.print("Enter Customer ID: ");
            String cid = sc.nextLine();
            System.out.print("Enter Month (e.g., 2025-04): ");
            String month = sc.nextLine();
            System.out.print("Enter Units Consumed: ");
            String units = sc.nextLine();

            bw.write(cid + "," + month + "," + units);
            bw.newLine();
            System.out.println("Reading recorded.");
        } catch (IOException e) {
            System.out.println("Error recording reading.");
        }
    }

    public static void viewReading(String customerId) {
        try (BufferedReader br = new BufferedReader(new FileReader(READING_FILE))) {
            String line;
            System.out.println("\n--- Meter Readings ---");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(customerId)) {
                    System.out.println("Month: " + parts[1] + ", Units: " + parts[2]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading readings.");
        }
    }
}
