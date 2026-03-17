import java.util.*;
public class User{
    private int acc_no;
    private String name;
    private double bankBalance;
    private int pin;
    private ArrayList<Transaction>transaction;
    public User(int acc_no, String name, int pin){
        this.acc_no=acc_no;
        this.name=name;
        this.pin=pin;
        this.transaction= new ArrayList<>();
    }
       public User(int acc_no, String name, int pin, double bankBalance){
        this.acc_no=acc_no;
        this.name=name;
        this.pin=pin;
        this.bankBalance=bankBalance;
         this.transaction= new ArrayList<>();
    }

    public int getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(int acc_no) {
        this.acc_no = acc_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBankBalance() {
        return bankBalance;
    }

    public void setBankBalance(double bankBalance) {
        this.bankBalance = bankBalance;
    }

    public int getPin() {
        return pin;
        
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }
}