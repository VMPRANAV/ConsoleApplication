
package operations;
import java.util.Scanner;

import models.ATM;
import models.User;
import services.TransactionService;
public interface ATMOperation {
    void execute(User user, Scanner sc, TransactionService transactionService, ATM atm);
}