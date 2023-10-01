import java.util.Scanner;

/**
 * 10.7
 */
public class ATM {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Account[] accounts = new Account[10];
        for (int i=0;i<10;i++){
            accounts[i] = new Account(i,100);
//            accounts[i].setId(i);
//            accounts[i].setBalance(100);
        }
        System.out.print("Enter an id:");
        int id = input.nextInt();
        if (id>=0 && id <=9) {
            while (true) {
                show_menu();
                int order = input.nextInt();
                switch (order){
                    case 1 : check_balance(accounts,id);
                        System.out.println("");;break;
                    case 2 : {
                        System.out.print("Enter an amount to withdraw:");
                        int money = input.nextInt();
                        withdraw(accounts,id,money);
                        System.out.println("");
                        break;
                    }
                    case 3 :{
                        System.out.print("Enter an amount to deposit:");
                        int money = input.nextInt();
                        deposit(accounts,id,money);
                        System.out.println("");
                        break;
                    }
                    case 4: System.exit(0);
                    default:
                        throw new IllegalStateException("Unexpected value: " + order);
                }

            }
        }
    }
    public static void show_menu(){
        System.out.println("Main menu");
        System.out.println("1:check balance");
        System.out.println("2:withdraw");
        System.out.println("3:deposit");
        System.out.println("4:exit");
        System.out.print("Enter a choice:");
    }
    public static void check_balance(Account[] accounts,int id){
        System.out.printf("The balance is %.1f\n",accounts[id].getBalance());
    }
    public static void withdraw(Account[] accounts,int id,int money){
        if (money<=accounts[id].getBalance()) {
            accounts[id].setBalance(accounts[id].getBalance() - money);
            check_balance(accounts, id);
        }
        else{
            System.out.println("Balance insufficient！\n");
        }
    }
    public static void deposit(Account[] accounts,int id,int money){
        accounts[id].setBalance(accounts[id].getBalance()+money);
        check_balance(accounts,id);
    }
}
