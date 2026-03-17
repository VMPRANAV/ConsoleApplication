package operations;
import java.util.Scanner;
import models.ATM;
import models.User;
import services.TransactionService;

public class DepositOperation implements ATMOperation {
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        System.out.println("Enter the amount to be deposited:");
        double amt = sc.nextDouble();
        transactionService.deposit(user, amt, atm);
    }
}