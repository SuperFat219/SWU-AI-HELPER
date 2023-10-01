import java.util.Date;

public class Account {

    private int id = 0;//用户名
    private double balance = 0;//余额
    private double annualInteresRate = 0;//当前利率
    private java.util.Date dateCreated;//存储开户日期

    public Account() {
        Date dateCreated = new Date();
        this.dateCreated = dateCreated;
    }

    public Account(int id, double balance) {
        Date dateCreated = new Date();
        this.dateCreated = dateCreated;
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;

    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getAnualInterestRate() {
        return annualInteresRate;

    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInteresRate = annualInterestRate;
    }

    public String getDateCreated() {
        return dateCreated.toString();
    }

    public double getMonthlyInterestRate() {
        return annualInteresRate / 12;

    }

    public double withDraw(double withdraw) {
        return this.balance = this.balance - withdraw;
    }

    public double deposit(double deposit) {
        return this.balance = this.balance + deposit;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                ", annualInteresRate=" + annualInteresRate +
                ", dateCreated=" + dateCreated +
                "}\n";
    }
}