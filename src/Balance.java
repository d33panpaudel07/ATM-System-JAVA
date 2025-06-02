public class Balance {
    private int id;
    private String accountNumber;
    private double balance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Balance(int id, String account_number, double balance) {
        this.id = id;
        this.accountNumber = account_number;
        this.balance = balance;
    }

    public Balance(String account_number, double balance) {
        this.accountNumber = account_number;
        this.balance = balance;
    }

    public Balance() {
        this.accountNumber = null;
        this.balance = 0.0;
    }

}
