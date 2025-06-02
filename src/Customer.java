public class Customer {
    private int id;
    private String name;
    private String accountNumber;
    private String mobileNumber;

    public Customer(int id, String name, String accountNumber, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
    }

    public Customer(String name, String accountNumber, String mobileNumber) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.mobileNumber = mobileNumber;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + id + ", Name=" + name + ", Account=" + accountNumber + ", Mobile Number="
                + mobileNumber + "]";
    }
}
