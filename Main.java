import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        User currentUser = null;

        while (true) {
            System.out.println("\n===== Electricity Billing System =====");
            System.out.println("1. Register as Customer");
            System.out.println("2. Register as Admin");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Username: ");
                    String cu = sc.nextLine();
                    System.out.print("Password: ");
                    String cp = sc.nextLine();
                    System.out.print("Customer ID: ");
                    String cid = sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Address: ");
                    String addr = sc.nextLine();
                    Customer newCust = new Customer(cu, cp, cid, name, addr);
                    if (Auth.registerCustomer(newCust)) {
                        System.out.println("Customer registered successfully.");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 2:
                    System.out.print("Username: ");
                    String au = sc.nextLine();
                    System.out.print("Password: ");
                    String ap = sc.nextLine();
                    Admin newAdmin = new Admin(au, ap);
                    if (Auth.registerAdmin(newAdmin)) {
                        System.out.println("Admin registered successfully.");
                    } else {
                        System.out.println("Registration failed.");
                    }
                    break;

                case 3:
                    System.out.print("Username: ");
                    String lu = sc.nextLine();
                    System.out.print("Password: ");
                    String lp = sc.nextLine();
                    currentUser = Auth.login(lu, lp);
                    if (currentUser != null) {
                        System.out.println("Login successful as " + currentUser.getRole());
                        handleDashboard(currentUser, sc);
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;

                case 4:
                    System.out.println("Exiting system...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void handleDashboard(User user, Scanner sc) {
        if (user instanceof Admin) {
            handleAdminDashboard((Admin) user, sc);
        } else if (user instanceof Customer) {
            handleCustomerDashboard((Customer) user, sc);
        }
    }

    public static void handleAdminDashboard(Admin admin, Scanner sc) {
        while (true) {
            System.out.println("\n===== Admin Dashboard =====");
            System.out.println("1. View All Customers");
            System.out.println("2. Record Meter Reading");
            System.out.println("3. Generate Bill");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    CustomerManager.viewAllCustomers();
                    break;
                case 2:
                    MeterReading.recordReading(sc);
                    break;
                case 3:
                    Billing.generateBill(sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public static void handleCustomerDashboard(Customer customer, Scanner sc) {
        while (true) {
            System.out.println("\n===== Customer Dashboard =====");
            System.out.println("1. View Meter Reading");
            System.out.println("2. View Bill");
            System.out.println("3. Pay Bill");
            System.out.println("4. Logout");
            System.out.print("Choice: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    MeterReading.viewReading(customer.getCustomerId());
                    break;
                case 2:
                    Billing.viewBill(customer.getCustomerId());
                    break;
                case 3:
                    Payment.processPayment(customer.getCustomerId(), sc);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
