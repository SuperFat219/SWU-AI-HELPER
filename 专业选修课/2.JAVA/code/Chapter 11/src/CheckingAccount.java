public class CheckingAccount extends Account {
    private double overDraftLimit;

    public CheckingAccount(int id, double balance, double overDraftLimit) {
        super(id, balance);
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }

    @Override
    public double withDraw(double withdraw) {
        if ((getBalance() - withdraw) < 0) {
            if ((getBalance() + overDraftLimit - withdraw) > 0) {
                setBalance(getBalance() + overDraftLimit-withdraw);
                setOverDraftLimit(0);
                return getBalance();
            } else {
                System.out.println("超出透支余额");
                return -1;
            }
        } else {
            setBalance(getBalance() - withdraw);
            return getBalance();
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nCheckingAccount{" +
                "overDraftLimit=" + overDraftLimit +
                "}\n";
    }
}
