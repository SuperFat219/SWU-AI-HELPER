import java.rmi.*;

public interface Exercise43_2RemoteInterface extends Remote {

  public double monthlyPayment(double loanAmount,
    double annualInterestRate, int numberOfYears) throws RemoteException;
  public double totalPayment(double loanAmount,
    double annualInterestRate, int numberOfYears) throws RemoteException;
}
