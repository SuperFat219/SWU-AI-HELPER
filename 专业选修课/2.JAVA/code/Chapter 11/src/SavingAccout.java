public class SavingAccout extends Account {
    public SavingAccout(int id, double balance) {
        super(id, balance);
    }

    @Override
    public double withDraw(double withdraw) {
        if (getBalance()<100)
        {
            System.out.println("账户余额不足！");
            return -1;
        }
        else {
            setBalance(getBalance()-withdraw);
            return getBalance();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
