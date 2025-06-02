import java.time.LocalDateTime;
import java.util.logging.Logger;

public class ATMService {
    BalanceService balanceService = new BalanceService();
    Logger logger = Logger.getLogger(ATMService.class.getName());

    public boolean deposit(String accNumber, double amount) {
        if (amount <= 0) {
            logger.warning("Invalid amount." + LocalDateTime.now());
            return false;
        }
        double current = balanceService.checkBalance(accNumber);
        if (current >= 0) {
            if (balanceService.updateBalance(accNumber, current + amount)) {
                logger.info("Deposited successfully." + LocalDateTime.now());
                return true;
            }
        }
        return false;
    }

    public boolean withdraw(String accNumber, double amount) {
        if (amount <= 0) {
            logger.warning("Invalid amount." + LocalDateTime.now());
            return false;
        }
        double current = balanceService.checkBalance(accNumber);
        if (current >= amount) {
            if (balanceService.updateBalance(accNumber, current - amount)) {
                logger.info("Withdrawn successfully." + LocalDateTime.now());
                return true;
            }
        } else {
            logger.info("Insufficient balance." + LocalDateTime.now());
        }
        return false;
    }

    public double checkBalance(String accNumber) {
        double current = balanceService.checkBalance(accNumber);
        if (current >= 0) {
            logger.info("Current Balance: " + current);
            return current;
        }
        return 0.0;
    }

    public boolean transfer(String from, String to, double amount) {
        if (amount <= 0) {
            logger.warning("Invalid amount." + LocalDateTime.now());
            return false;
        }
        double fromBal = balanceService.checkBalance(from);
        double toBal = balanceService.checkBalance(to);

        if (fromBal >= amount && toBal != -1) {
            if (balanceService.updateBalance(from, fromBal - amount) &&
                    balanceService.updateBalance(to, toBal + amount)) {
                logger.info("Transfer successful.");
                return true;
            }
        } else {
            logger.warning("Transfer failed. Check balances/accounts.");
        }
        return false;
    }
}
