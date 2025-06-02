import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class BalanceService {
    Logger logger = Logger.getLogger(BalanceService.class.getName());

    public double checkBalance(String accountNumber) {
        try (Connection connection = DBConnection.getConnection()) {
            String query = "SELECT * FROM balance WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, accountNumber);

            logger.info("Calculating balance from account number..." + LocalDateTime.now());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                logger.info("Balance found for give account number..." + LocalDateTime.now());
                return resultSet.getDouble("balance");
            }

            logger.info("Couldn't find balance..." + LocalDateTime.now());
            return 0.0;
        } catch (Exception e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return 0.0;
        }
    }

    public boolean updateBalance(String accountNumber, double newBalance) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE balance SET balance = ? WHERE account_number = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setDouble(1, newBalance);
            preparedStatement.setString(2, accountNumber);

            logger.info("Executing query to update balance..." + LocalDateTime.now());

            if (preparedStatement.executeUpdate() == 0) {
                logger.info("No balance updated, Account may not exist..." + LocalDateTime.now());
                return false;
            }

            logger.info("Query execution successful..." + LocalDateTime.now());
            return true;
        } catch (Exception e) {
            logger.warning("Error in executing query..." + LocalDateTime.now() + "\n" + e.toString());
            return false;
        }
    }
}
