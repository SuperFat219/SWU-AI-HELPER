public class TestAccount {
    public static void main(String[] args) {
        Account account = new Account(1, 2000);
        account.withDraw(500);
        System.out.println(account.toString());

        CheckingAccount checkingAccount = new CheckingAccount(2, 3000, 1000);
        checkingAccount.deposit(500);//3500+1000
        checkingAccount.withDraw(3000);//500+1000
//        System.out.println(checkingAccount.toString());
        checkingAccount.withDraw(1450);//0+50
        checkingAccount.withDraw(200);//fail
        System.out.println(checkingAccount.toString());

        SavingAccout savingAccout = new SavingAccout(3, 2000);
        savingAccout.withDraw(1950);
        savingAccout.withDraw(100);
        System.out.println(savingAccout.toString());


    }
}
