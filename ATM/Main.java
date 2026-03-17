import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import models.*;
import services.*;
import operations.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<User> usersList = new ArrayList<>();
        ArrayList<Transaction> transactionHistory = new ArrayList<>();
        BankService bankService = new BankService(usersList);
        TransactionService transactionService = new TransactionService(transactionHistory, bankService);
        ATM atm = new ATM(1000.00);

        bankService.getTotalUsers().add(new User(123456789, "Pranav", 123, 1000.00));
        bankService.getTotalUsers().add(new User(123456787, "Navin", 456, 500.00));
        bankService.getTotalUsers().add(new User(123456786, "Velu", 457, 200.00));

        Map<Integer, ATMOperation> operations = new HashMap<>();
        operations.put(1, new WithdrawOperation());
        operations.put(2, new DepositOperation());
        operations.put(3, new CheckBalanceOperation());
        operations.put(4, new FundTransferOperation());
        operations.put(5, new PinChangeOperation());
        operations.put(6, new MiniStatementOperation());

        try (Scanner sc = new Scanner(System.in)) {
            User user = null;
            boolean sessionActive = false;

            do {
                System.out.println("Enter Account Number:");
                int inpAccNo = sc.nextInt();
                System.out.println("Enter PIN:");
                int inpPin = sc.nextInt();

                if (inpAccNo == 99999 && inpPin == 555) {
                    transactionService.adminDashboard(atm);
                    System.out.println("Exiting Admin View...");
                    return; // safe: scanner auto-closes
                } else {
                    user = bankService.validateUser(inpAccNo, inpPin);
                }

                if (user != null) {
                    sessionActive = true;
                    System.out.println("Login Successful!\n");
                } else {
                    System.out.println("Invalid Credentials. Try again.\n");
                }
            } while (user == null);

            while (sessionActive) {
                System.out.println("\n--- Welcome to ATM, " + user.getName() + " ---");
                System.out.println("1. Withdraw");
                System.out.println("2. Deposit");
                System.out.println("3. Check Balance");
                System.out.println("4. Fund Transfer");
                System.out.println("5. Pin Change");
                System.out.println("6. Mini Statement");
                System.out.println("Choose an option: ");

                int ch = sc.nextInt();
                sc.nextLine();

                ATMOperation operation = operations.get(ch);
                if (operation != null) {
                    operation.execute(user, sc, transactionService, atm);
                } else {
                    System.out.println("Invalid Option selected.");
                }

                System.out.println("\nDo You want to Continue (Y/N)?");
                char c = sc.nextLine().toUpperCase().charAt(0);
                if (c == 'N') sessionActive = false;
            }

            System.out.println("Thank you for using the ATM!");
        }
    }
}