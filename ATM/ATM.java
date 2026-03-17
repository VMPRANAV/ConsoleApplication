import java.util.ArrayList;

public class ATM{
    private ArrayList<Transaction>TransactionHistory;
    private double TotalAmountAvailable;
    private ArrayList<User> totalUsers;
    private int nextId=1;
 public enum TransactionType {
        WITHDRAW,
        DEPOSIT,
        CHECK_BALANCE,
        PIN_CHANGE,
        FUND_TRANSFER
    }
    
    public ATM( double TotalAmountAvailable,ArrayList<Transaction>TransactionHistory, ArrayList<User>totalUsers){
        this.TransactionHistory=TransactionHistory;
        this.TotalAmountAvailable=TotalAmountAvailable;
        this.totalUsers= totalUsers;
    }
    public ArrayList<Transaction> getTransactionHistory() {
        return TransactionHistory;
    }

    public void setTransactionHistory(ArrayList<Transaction> transactionHistory) {
        TransactionHistory = transactionHistory;
    }

    public double getTotalAmountAvailable() {
        return TotalAmountAvailable;
    }

    public void setTotalAmountAvailable(double totalAmountAvailable) {
        TotalAmountAvailable = totalAmountAvailable;
    }

    public ArrayList<User> getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(ArrayList<User> totalUsers) {
        this.totalUsers = totalUsers;
    }
    public User validateUser(  int inpAccNo, int inpPin){
        for(int i=0;i<totalUsers.size();i++){
            User u= totalUsers.get(i);
            if(u.getAcc_no()==inpAccNo){
                if(u.getPin()==inpPin){
                    return u;
                }
                
            }
            
        }
        return null;

    }

     public User findUser (  int inpAccNo){
        for(int i=0;i<totalUsers.size();i++){
            User u= totalUsers.get(i);
            if(u.getAcc_no()==inpAccNo){
               return u;
                
            }
            
        }
        return null;

    }
    //Withdraw Function
    public  void withdraw(User user, double amt){
         if (amt % 100 != 0) {
        System.out.println("Invalid amount. This ATM only dispenses 100, 200, and 500 notes.");
        return;
    }
        if(amt<0){
            System.out.println("Amount Can't be Negative ");
            return;
        }
        if(amt>TotalAmountAvailable){
            System.out.println("Insufficent Balance in ATM Kindly try another amount");
            return;
        }
        else{
            if(amt>user.getBankBalance()){
                  System.out.println("Insufficent Balance in Account  Kindly try another amount");
            return;
            }
            else {
                user.setBankBalance(user.getBankBalance()-amt);
                TotalAmountAvailable-=amt;
                  Transaction t= new Transaction(nextId++, user.getAcc_no(), "WITHDRAW", amt);
        user.getTransaction().add(t);
        this.TransactionHistory.add(t);
                System.out.println("Sucessfully withdraw  Rs."+amt+" Balance : "+user.getBankBalance());
            }
            
        }

    }
    // Deposit Function
    public void deposit(User user, double amt){
         if(amt<0){
            System.out.println("Amount Can't be Negative ");
            return;
        }
        user.setBankBalance(user.getBankBalance()+amt);
        TotalAmountAvailable+=amt;
        Transaction t= new Transaction(nextId++, user.getAcc_no(), "DEPOSIT", amt);
        user.getTransaction().add(t);
        this.TransactionHistory.add(t);
       
         System.out.println("Sucessfully deposited  Rs."+amt+" Balance : "+user.getBankBalance());

    }
    public void checkBalance(User user){
         Transaction t= new Transaction(nextId++, user.getAcc_no(), "BALANCE", 0);
        user.getTransaction().add(t);
        this.TransactionHistory.add(t);
        System.out.println("User Balance: "+user.getBankBalance());
    }
    public void miniStatement(User user){
        ArrayList<Transaction>history= user.getTransaction();
        if(history.isEmpty()){
            System.out.println("NO Transaction if found");
            return;
        }
        int start= Math.max(0,history.size()-5);
        for(int i= start;i<history.size();i++){
          Transaction t= history.get(i);
        System.out.println(t.getT_id() + " | " + t.getTypes() + " | " + t.getAmount());
        }
    }
        public void fundtransfer(User user, int inp_accno, double amt){
        User recipient = findUser(inp_accno);
if(recipient == null){
System.out.println("Recipient account not found.");
return;
}
  if(amt<0){
            System.out.println("Amount Can't be Negative ");
            return;
        }
        if(amt>user.getBankBalance()){
                  System.out.println("Insufficent Balance in Account  Kindly try another amount");
            return;
            }
            user.setBankBalance(user.getBankBalance()-amt);
            recipient.setBankBalance(recipient.getBankBalance()+amt);
             Transaction t1= new Transaction(nextId++, user.getAcc_no(), "FUND_TRANSFER_FROM", amt);
        user.getTransaction().add(t1);
        this.TransactionHistory.add(t1);
           Transaction t2= new Transaction(nextId++, user.getAcc_no(), "FUND_TRANSFER_TO", amt);
        recipient.getTransaction().add(t2);
        this.TransactionHistory.add(t2);
                System.out.println("Sucessfully withdraw  Rs."+amt+" Balance : "+user.getBankBalance());


        }
        public void pinChange(User user, int currPin, int NewPin){
            if(user.getPin()!=currPin){
                System.out.println("Pin Incorrect Access Denied");
                return;
            
            }
            if(currPin==NewPin){
                System.out.println("Current Pin and New Pin are Same");
                return;
            }

user.setPin(NewPin);
Transaction t = new Transaction(nextId++, user.getAcc_no(), "PIN_CHANGE", 0);
user.getTransaction().add(t);
this.getTransactionHistory().add(t);

System.out.println("Pin Change is Updated");
        }
        public void adminDashboard() {
    double totalDeposits = 0;
    java.util.HashSet<Integer> uniqueUsers = new java.util.HashSet<>();

    for (Transaction t : TransactionHistory) {
        uniqueUsers.add(t.getUser_id());
        if (t.getTypes().equals("DEPOSIT")) {
            totalDeposits += t.getAmount();
        }
    }

    System.out.println("--- ADMIN DASHBOARD ---");
    System.out.println("Total Transactions: " + TransactionHistory.size());
    System.out.println("Total Unique Users: " + uniqueUsers.size());
    System.out.println("Total Amount Deposited: Rs." + totalDeposits);
    System.out.println("ATM Cash Balance: Rs." + TotalAmountAvailable);
}
    
}