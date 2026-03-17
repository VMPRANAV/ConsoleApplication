package models;
public class Transaction{
    private int t_id;
private int user_id;
    private String date;
    private String  types;
    private double amount;
    public Transaction(int t_id, int user_id,String types, double amount){
        this.t_id=t_id;
        this.user_id=user_id;
       this.types=types;
       this.amount=amount;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


} 