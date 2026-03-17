public class ATM {
    private double totalAmountAvailable;

    public ATM(double totalAmountAvailable) {
        this.totalAmountAvailable = totalAmountAvailable;
    }

    public double getTotalAmountAvailable() {
        return totalAmountAvailable;
    }

    public void setTotalAmountAvailable(double totalAmountAvailable) {
        this.totalAmountAvailable = totalAmountAvailable;
    }
}