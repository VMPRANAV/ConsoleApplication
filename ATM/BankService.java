
    import java.util.ArrayList;

public class BankService {
    private ArrayList<User> totalUsers;

    public BankService(ArrayList<User> totalUsers) {
        this.totalUsers = totalUsers;
    }

    public ArrayList<User> getTotalUsers() {
        return totalUsers;
    }

    public User validateUser(int inpAccNo, int inpPin) {
        for (User u : totalUsers) {
            if (u.getAcc_no() == inpAccNo && u.getPin() == inpPin) {
                return u;
            }
        }
        return null;
    }

    public User findUser(int inpAccNo) {
        for (User u : totalUsers) {
            if (u.getAcc_no() == inpAccNo) {
                return u;
            }
        }
        return null;
    }
}

