package operations;

import java.util.Scanner;

import models.ATM;
import models.User;
import services.TransactionService;

public class CheckBalanceOperation implements ATMOperation {
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        transactionService.checkBalance(user);
    }
}
