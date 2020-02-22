package rocks.zipcode.atm.bank;

public class SavingAccount extends Account {

    private double annualInterestRate;
    private double monthlyInterestRate;
    private double totalInterest;

    public SavingAccount(AccountData accountData) {
        super(accountData);
    }

    public void setAnnualInterestRate (double annualInterestRate) {
        monthlyInterestRate = annualInterestRate / 12;
    }

    public void calculateMonthlyInterest() {
        totalInterest = totalInterest + getBalance() * monthlyInterestRate;
    }
}
