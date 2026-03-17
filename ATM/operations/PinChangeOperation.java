package operations;

import java.util.Scanner;

import models.ATM;
import models.User;
import services.TransactionService;

public class PinChangeOperation implements ATMOperation{
    public void execute(User user, Scanner sc, TransactionService transactionService, ATM atm) {
        System.out.println("Enter the Current Pin: ");
        int currPin = sc.nextInt();
        System.out.println("Enter the New Pin: ");
        int newPin = sc.nextInt();
        transactionService.pinChange(user, currPin, newPin);
    }
}
