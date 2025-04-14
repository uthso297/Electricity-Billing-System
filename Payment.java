import java.io.*;
import java.util.*;

public class Payment {
    private static final String BILL_FILE = "bills.txt";
    private static final String PAYMENT_FILE = "payments.txt";

    public static void processPayment(String customerId, Scanner sc) {
        try {
            System.out.print("Enter Month to pay for (e.g., 2025-04): ");
            String month = sc.nextLine();

            File inputFile = new File(BILL_FILE);
            File tempFile = new File("temp_bills.txt");
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));

            String line;
            boolean paid = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(customerId) && parts[1].equals(month) && parts[4].equals("unpaid")) {
                    parts[4] = "paid";
                    paid = true;

                    try (BufferedWriter payWriter = new BufferedWriter(new FileWriter(PAYMENT_FILE, true))) {
                        payWriter.write(customerId + "," + month + "," + parts[3] + ",paid");
                        payWriter.newLine();
                    }

                    bw.write(String.join(",", parts));
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }

            br.close();
            bw.close();

            if (!paid) {
                System.out.println("No unpaid bill found for this month.");
            } else {
                if (inputFile.delete()) {
                    tempFile.renameTo(inputFile);
                    System.out.println("Payment successful.");
                } else {
                    System.out.println("Error updating bill status.");
                }
            }
        } catch (IOException e) {
            System.out.println("Payment error.");
        }
    }
}
