import javax.swing.*;
import java.awt.*;

public class ATMUI extends JFrame {
    private ATMService atmService = new ATMService();
    private CustomerService customerService = new CustomerService();

    private JTextField accNumberField = new JTextField(15);
    private JTextField amountField = new JTextField(15);
    private JTextField toAccField = new JTextField(15);

    private JTextField nameField = new JTextField(15);
    private JTextField mobileField = new JTextField(15);

    private JTextArea outputArea = new JTextArea(10, 40);

    public ATMUI() {
        setTitle("ATM Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.add(new JLabel("Account Number:"));
        inputPanel.add(accNumberField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        inputPanel.add(new JLabel("To Account (for Transfer):"));
        inputPanel.add(toAccField);
        inputPanel.add(new JLabel("Name (for Create):"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Mobile (for Create):"));
        inputPanel.add(mobileField);

        JPanel buttonPanel = new JPanel();
        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton checkBtn = new JButton("Check Balance");
        JButton transferBtn = new JButton("Transfer");
        JButton createBtn = new JButton("Create Account");
        JButton getCustomerBtn = new JButton("View Customer");
        JButton deleteBtn = new JButton("Delete Account");

        buttonPanel.add(depositBtn);
        buttonPanel.add(withdrawBtn);
        buttonPanel.add(checkBtn);
        buttonPanel.add(transferBtn);
        buttonPanel.add(createBtn);
        buttonPanel.add(getCustomerBtn);
        buttonPanel.add(deleteBtn);

        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        depositBtn.addActionListener(e -> {
            String acc = accNumberField.getText();
            double amt = parseAmount();
            if (atmService.deposit(acc, amt)) {
                outputArea.append("Deposit successful.\n");
            } else {
                outputArea.append("Deposit failed.\n");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String acc = accNumberField.getText();
            double amt = parseAmount();
            if (atmService.withdraw(acc, amt)) {
                outputArea.append("Withdraw successful.\n");
            } else {
                outputArea.append("Withdraw failed.\n");
            }
        });

        checkBtn.addActionListener(e -> {
            String acc = accNumberField.getText();
            double bal = atmService.checkBalance(acc);
            outputArea.append("Current Balance: " + bal + "\n");
        });

        transferBtn.addActionListener(e -> {
            String from = accNumberField.getText();
            String to = toAccField.getText();
            double amt = parseAmount();
            if (atmService.transfer(from, to, amt)) {
                outputArea.append("Transfer successful.\n");
            } else {
                outputArea.append("Transfer failed.\n");
            }
        });

        createBtn.addActionListener(e -> {
            String name = nameField.getText();
            String acc = accNumberField.getText();
            String mobile = mobileField.getText();
            if (customerService.createCustomer(name, acc, mobile)) {
                outputArea.append("Account created successfully.\n");
            } else {
                outputArea.append("Account creation failed.\n");
            }
        });

        getCustomerBtn.addActionListener(e -> {
            String acc = accNumberField.getText();
            Customer customer = customerService.getCustomerByAccountNumber(acc);
            if (customer != null) {
                outputArea.append("Customer Details:\n" + customer + "\n");
            } else {
                outputArea.append("Customer not found.\n");
            }
        });

        deleteBtn.addActionListener(e -> {
            String acc = accNumberField.getText();
            if (customerService.deleteCustomerByAccountNumber(acc)) {
                outputArea.append("Account deleted successfully.\n");
            } else {
                outputArea.append("Account deletion failed.\n");
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private double parseAmount() {
        try {
            return Double.parseDouble(amountField.getText());
        } catch (Exception e) {
            outputArea.append("Invalid amount.\n");
            return -1;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ATMUI::new);
    }
}