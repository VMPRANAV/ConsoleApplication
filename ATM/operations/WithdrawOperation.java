package operations;
import java.util.Scanner;
import models.ATM;
import models.User;
import services.TransactionService;

public class WithdrawOperation implements ATMOperation {
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        System.out.println("Enter the amount to be withdrawn:");
        double amt = sc.nextDouble();
        transactionService.withdraw(user, amt, atm);
    }
}