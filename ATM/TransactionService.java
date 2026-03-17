import java.util.ArrayList;

public class TransactionService {
    private ArrayList<Transaction> transactionHistory;
    private BankService bankService; // Needs this to find users for fund transfer
    private int nextId = 1;

    public TransactionService(ArrayList<Transaction> transactionHistory, BankService bankService) {
        this.transactionHistory = transactionHistory;
        this.bankService = bankService;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    private boolean validateAmount(double amt) {
        if (amt < 0) {
            System.out.println("Amount Can't be Negative");
            return false;
        }
        return true;
    }

    // Pass the ATM object so we know how much physical cash is left!
    public void withdraw(User user, double amt, ATM atm) {
        if (amt % 100 != 0) {
            System.out.println("Invalid amount. This ATM only dispenses 100, 200, and 500 notes.");
            return;
        }
        if (!validateAmount(amt)) return;

        if (amt > atm.getTotalAmountAvailable()) { // Checking ATM cash
            System.out.println("Insufficient Balance in ATM. Kindly try another amount.");
            return;
        }

        if (amt > user.getBankBalance()) {
            System.out.println("Insufficient Balance in Account. Kindly try another amount.");
            return;
        }

        user.setBankBalance(user.getBankBalance() - amt);
        atm.setTotalAmountAvailable(atm.getTotalAmountAvailable() - amt); // Updating ATM cash

        Transaction t = new Transaction(nextId++, user.getAcc_no(), "WITHDRAW", amt);
        user.getTransaction().add(t);
        this.transactionHistory.add(t);
        System.out.println("Successfully withdrew Rs." + amt + " | Balance: " + user.getBankBalance());
    }

    // Pass the ATM object here too, so the physical machine gets the deposited cash
    public void deposit(User user, double amt, ATM atm) {
        if (!validateAmount(amt)) return;

        user.setBankBalance(user.getBankBalance() + amt);
        atm.setTotalAmountAvailable(atm.getTotalAmountAvailable() + amt); // Updating ATM cash

        Transaction t = new Transaction(nextId++, user.getAcc_no(), "DEPOSIT", amt);
        user.getTransaction().add(t);
        this.transactionHistory.add(t);
        System.out.println("Successfully deposited Rs." + amt + " | Balance: " + user.getBankBalance());
    }

    public void checkBalance(User user) {
        Transaction t = new Transaction(nextId++, user.getAcc_no(), "BALANCE",user.getBankBalance());
        user.getTransaction().add(t);
        this.transactionHistory.add(t);
        System.out.println("User Balance: " + user.getBankBalance());
    }

    public void miniStatement(User user) {
        ArrayList<Transaction> history = user.getTransaction();
        if (history.isEmpty()) {
            System.out.println("No Transactions found.");
            return;
        }
        int start = Math.max(0, history.size() - 5);
        for (int i = start; i < history.size(); i++) {
            Transaction t = history.get(i);
            System.out.println(t.getT_id() + " | " + t.getTypes() + " | " + t.getAmount());
        }
    }

    public void fundtransfer(User user, int inp_accno, double amt) {
        // We use BankService here to find the recipient!
        User recipient = bankService.findUser(inp_accno); 
        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }
        if (!validateAmount(amt)) return;
        if (amt > user.getBankBalance()) {
            System.out.println("Insufficient Balance in Account.");
            return;
        }

        user.setBankBalance(user.getBankBalance() - amt);
        recipient.setBankBalance(recipient.getBankBalance() + amt);

        Transaction t1 = new Transaction(nextId++, user.getAcc_no(), "FUND_TRANSFER_FROM", amt);
        user.getTransaction().add(t1);
        this.transactionHistory.add(t1);

        Transaction t2 = new Transaction(nextId++, recipient.getAcc_no(), "FUND_TRANSFER_TO", amt);
        recipient.getTransaction().add(t2);
        this.transactionHistory.add(t2);

        System.out.println("Successfully transferred Rs." + amt + " | Balance: " + user.getBankBalance());
    }

    public void pinChange(User user, int currPin, int newPin) {
        if (user.getPin() != currPin) {
            System.out.println("Pin Incorrect. Access Denied.");
            return;
        }
        if (currPin == newPin) {
            System.out.println("Current Pin and New Pin are the same.");
            return;
        }

        user.setPin(newPin);
        Transaction t = new Transaction(nextId++, user.getAcc_no(), "PIN_CHANGE", 0);
        user.getTransaction().add(t);
        this.transactionHistory.add(t);
        System.out.println("Pin successfully updated.");
    }
    
    // Admin Dashboard uses the global transaction history, so it can stay here (or go to an AdminService)
    public void adminDashboard(ATM atm) {
        double totalDeposits = 0;
        java.util.HashSet<Integer> uniqueUsers = new java.util.HashSet<>();

        for (Transaction t : transactionHistory) {
            uniqueUsers.add(t.getUser_id());
            if (t.getTypes().equals("DEPOSIT")) {
                totalDeposits += t.getAmount();
            }
        }

        System.out.println("--- ADMIN DASHBOARD ---");
        System.out.println("Total Transactions: " + transactionHistory.size());
        System.out.println("Total Unique Users: " + uniqueUsers.size());
        System.out.println("Total Amount Deposited: Rs." + totalDeposits);
        System.out.println("ATM Cash Balance: Rs." + atm.getTotalAmountAvailable());
    }
}