import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ATMService atmService = new ATMService();
        CustomerService customerService = new CustomerService();

        while (true) {
            System.out.println("\n--- ATM Text Menu ---");
            System.out.println("1. Create Customer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Check Balance");
            System.out.println("6. View Customer");
            System.out.println("7. Delete Customer");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Account Number: ");
                    String acc = sc.nextLine();
                    System.out.print("Mobile: ");
                    String mobile = sc.nextLine();
                    if (customerService.createCustomer(name, acc, mobile)) {
                        System.out.println("Customer created.");
                    } else {
                        System.out.println("Failed to create customer.");
                    }
                    break;
                case 2:
                    System.out.print("Account Number: ");
                    acc = sc.nextLine();
                    System.out.print("Amount: ");
                    double depAmt = sc.nextDouble();
                    sc.nextLine();
                    if (atmService.deposit(acc, depAmt)) {
                        System.out.println("Deposit successful.");
                    } else {
                        System.out.println("Deposit failed.");
                    }
                    break;
                case 3:
                    System.out.print("Account Number: ");
                    acc = sc.nextLine();
                    System.out.print("Amount: ");
                    double wdAmt = sc.nextDouble();
                    sc.nextLine();
                    if (atmService.withdraw(acc, wdAmt)) {
                        System.out.println("Withdraw successful.");
                    } else {
                        System.out.println("Withdraw failed.");
                    }
                    break;
                case 4:
                    System.out.print("From Account: ");
                    String from = sc.nextLine();
                    System.out.print("To Account: ");
                    String to = sc.nextLine();
                    System.out.print("Amount: ");
                    double tfAmt = sc.nextDouble();
                    sc.nextLine();
                    if (atmService.transfer(from, to, tfAmt)) {
                        System.out.println("Transfer successful.");
                    } else {
                        System.out.println("Transfer failed.");
                    }
                    break;
                case 5:
                    System.out.print("Account Number: ");
                    acc = sc.nextLine();
                    double bal = atmService.checkBalance(acc);
                    System.out.println("Balance: " + bal);
                    break;
                case 6:
                    System.out.print("Account Number: ");
                    acc = sc.nextLine();
                    Customer c = customerService.getCustomerByAccountNumber(acc);
                    if (c != null) {
                        System.out.println(c);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 7:
                    System.out.print("Account Number: ");
                    acc = sc.nextLine();
                    if (customerService.deleteCustomerByAccountNumber(acc)) {
                        System.out.println("Customer deleted.");
                    } else {
                        System.out.println("Delete failed.");
                    }
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}