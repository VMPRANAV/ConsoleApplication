package operations;

import java.util.Scanner;

import models.ATM;
import models.User;
import services.TransactionService;

public class MiniStatementOperation implements ATMOperation {
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        transactionService.miniStatement(user);
    }
}
