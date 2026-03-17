package operations;

import java.util.Scanner;

import models.ATM;
import models.User;
import services.TransactionService;

public class FundTransferOperation implements ATMOperation {
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        System.out.println("Enter the Account Details to Transfer: ");
        int accNo = sc.nextInt();
        System.out.println("Enter the amount to Transfer:");
        double amt = sc.nextDouble();
        transactionService.fundtransfer(user, accNo, amt);
    }
}
