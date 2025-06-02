# Java ATM JDBC Project

A simple Java-based ATM simulation using JDBC and MySQL. This project demonstrates basic banking operations (deposit, withdraw, transfer, balance check, and customer management) with a Swing-based user interface.

## Features

-   Deposit, withdraw, and transfer money between accounts
-   Check account balance
-   Create, update, retrieve, and delete customer records
-   Simple Java Swing UI for ATM operations
-   Uses JDBC for MySQL database connectivity
-   Logging for all operations

## Folder Structure

```
projectJDBC/
├── src/        # Java source files
│   ├── App.java
│   ├── ATMService.java
│   ├── ATMUI.java
│   ├── Balance.java
│   ├── BalanceService.java
│   ├── Customer.java
│   ├── CustomerService.java
│   └── DBConnection.java
├── lib/        # External libraries (if any)
├── bin/        # Compiled output (auto-generated)
└── README.md
```

## Prerequisites

-   Java 8 or higher
-   MySQL server running locally
-   MySQL JDBC Driver (add to `lib/` if not using Maven/Gradle)
-   (Optional) Visual Studio Code with Java extensions

## Database Setup

1. Create a database named `atm` in MySQL:

    ```sql
    CREATE DATABASE atm;
    ```

2. Create the required tables:

    ```sql
    CREATE TABLE customer (
        id INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(100),
        account_number VARCHAR(20) UNIQUE,
        mobile_number VARCHAR(20)
    );

    CREATE TABLE balance (
        id INT AUTO_INCREMENT PRIMARY KEY,
        account_number VARCHAR(20) UNIQUE,
        balance DOUBLE,
        FOREIGN KEY (account_number) REFERENCES customer(account_number)
    );
    ```

3. Update the database credentials in [`DBConnection.java`](src/DBConnection.java) if needed.

## Building and Running

1. **Compile the project:**

    ```sh
    javac -cp "lib/*" -d bin src/*.java
    ```

2. **Run the ATM UI:**

    ```sh
    java -cp "bin:lib/*" ATMUI
    ```

    - If you want to run from VS Code, right-click `ATMUI.java` and select "Run Java".

## Usage

-   **ATMUI** provides a graphical interface for deposit, withdraw, transfer, and balance check.
-   Enter the account number and amount, then click the desired operation.
-   For transfer, fill both "Account Number" and "To Account".

## Customization

-   To add or manage customers, use the methods in `CustomerService.java` or extend the UI.
-   You can further enhance the UI or add authentication as needed.

## License

This project is open source and available under the [MIT License](LICENSE).

---

**Contributions are welcome!**
