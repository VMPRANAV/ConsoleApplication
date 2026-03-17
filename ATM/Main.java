import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATM atm= new ATM(1000.00,new ArrayList<>(),new ArrayList<>());
        User user1= new User(123456789, "Pranav", 123, 1000.00);
 User user2= new User(123456787, "Navin", 456);
  User user3= new User(123456786, "Velu", 457, 200);
  atm.getTotalUsers().add(user1);
   atm.getTotalUsers().add(user2);
    atm.getTotalUsers().add(user3);
    Scanner sc= new Scanner(System.in);
     User user=null;
   boolean st=false;
   do{ 
     int inpAccNo=sc.nextInt();
    int inpPin=sc.nextInt();
    if(inpAccNo==99999 && inpPin==555){
        atm.adminDashboard();
        return;
    }
    else
    user= atm.validateUser(inpAccNo,inpPin);
    if(user!=null){
      st=true;
        System.out.println("Login Sucessfull");


    }
    else
        System.out.println("Login Valid");
} while(user==null);

    while(st){
        System.out.println("Welcome to ATM");
        System.out.println("1. Withdraw");
            System.out.println("2.Deposit");
                System.out.println("3.Check Balance");
                    System.out.println("4.Fund Transfer");
                        System.out.println("5. Pin Change");
                        System.out.println("6. Mini Statement");
                        System.out.println("Choose the optinon ");
                    int ch=sc.nextInt(); 
                    sc.nextLine();
                    switch(ch){
                        case 1:
                            System.out.println("Enter the amount to be withdraw");
                            double amt= sc.nextDouble();
                            atm.withdraw(user,amt);
                            break;
                            case 2:
                                  System.out.println("Enter the amount to be deposit");
                            double amt1= sc.nextDouble();
                                atm.deposit(user,amt1);
                                break;
                                case 3: 
                                 atm.checkBalance(user);
                                 break;
                                case 4: 
                              System.out.println("Enter the Account Details to Transfer: ");
                              int inp_accno=sc.nextInt();
                              System.out.println("Enter the amount to Transfer");
                              double amt2= sc.nextDouble();

                               atm.fundtransfer(user,inp_accno,amt2);
                                break;
                                case 5: 
                                  System.out.println("Enter the Current Pin: ");
                                  int currPin=sc.nextInt();
                                System.out.println("Enter the New Pin: ");
                                int newPin=sc.nextInt();
                                atm.pinChange(user,currPin,newPin); 
                                break;
                                case 6:
                                atm.miniStatement(user);
                                break;

                    }
                    sc.nextLine();
                    System.out.println("Do You want to Continue(Y/N)");
                    char c=sc.nextLine().charAt(0);
                    if(c=='Y')st=true;
                    else st=false;

    }

    }
}
