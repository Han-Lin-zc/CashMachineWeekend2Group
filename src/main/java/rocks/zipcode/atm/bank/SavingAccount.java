package rocks.zipcode.atm.bank;

public class SavingAccount extends Account {

    private double annualInterestRate = .02;

    //startBalance = account balance;

    public SavingAccount(AccountData accountData) {
        super(accountData);
    }

    //start balance
    public float getBalance() {
        return getBalance();
    }



    //adds the amount to the balance. Calculates total deposit amount
    public float calculateAnnualInterestRate() {
      float interestGained = (float) (getBalance() * annualInterestRate);
      return interestGained;
    }
    public void addInterestGainedToBalance() {

        deposit(calculateAnnualInterestRate());

    }

}
