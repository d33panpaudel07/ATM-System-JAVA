import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class CustomerService {
    Logger logger = Logger.getLogger(CustomerService.class.getName());

    public boolean createCustomer(String name, String accNumber, String mobile) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO customer (name, account_number, mobile_number)VALUES (?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, accNumber);
            preparedStatement.setString(3, mobile);
            logger.info("Executing query to create customer..." + LocalDateTime.now());
            preparedStatement.executeUpdate();
            logger.info("Customer created successfully..." + LocalDateTime.now());
            return true;
        } catch (SQLException e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return false;
        }
    }

    public boolean updateCustomer(int id, String name, String accNumber, String mobile) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE customer SET name = ?, account_number = ?, mobile_number = ? WHERE id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, accNumber);
            preparedStatement.setString(3, mobile);
            preparedStatement.setInt(4, id);
            logger.info("Executing query to update customer..." + LocalDateTime.now());
            preparedStatement.execute();
            logger.info("Customer updated successfully..." + LocalDateTime.now());
            return true;
        } catch (SQLException e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return false;
        }
    }

    public Customer getCustomerByAccountNumber(String accountNumber) {

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM customer WHERE account_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            logger.info("Executing query to get customer by account number..." + LocalDateTime.now());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.info("Customer found successfully..." + LocalDateTime.now());
                return new Customer(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("account_number"), resultSet.getString("mobile_number"));
            }
            logger.info("Account doesn't exist" + LocalDateTime.now());
            return null;
        } catch (SQLException e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return null;
        }
    }

    public boolean deleteCustomerByAccountNumber(String accountNumber) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "DELETE FROM customer where account_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);
            logger.info("Executing query to delete customer by account number..." + LocalDateTime.now());
            preparedStatement.execute();
            logger.info("Customer deleted successfully..." + LocalDateTime.now());
            return true;
        } catch (SQLException e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return false;
        }
    }
}
