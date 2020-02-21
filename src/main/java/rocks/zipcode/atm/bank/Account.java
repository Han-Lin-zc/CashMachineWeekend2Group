package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public abstract class Account {

    //fields
    private AccountData accountData;

    //constructor
    public Account(AccountData accountData) {
        this.accountData = accountData;
    }

    //methods here and below
    public AccountData getAccountData() {
        return accountData;
    }

    //takes in a float for parameter
    public void deposit(int amount) {
        updateBalance(getBalance() + amount);
    }

    // taking in a float for parameter
    public boolean withdraw(int amount) {
        if (canWithdraw(amount)) {
            updateBalance(getBalance() - amount);
            return true;
        } else {
            return false;
        }
    }

    //float again
    protected boolean canWithdraw(int amount) {
        return getBalance() >= amount;
    }

    public int getBalance() {
        return accountData.getBalance();
    }

    //float
    private void updateBalance(int newBalance) {
        accountData = new AccountData(accountData.getId(), accountData.getName(), accountData.getEmail(),
                newBalance);
    }
}
