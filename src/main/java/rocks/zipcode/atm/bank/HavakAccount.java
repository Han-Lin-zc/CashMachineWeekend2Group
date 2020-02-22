package rocks.zipcode.atm.bank;

public class HavakAccount extends Account {

    private static final int OVERDRAFT_LIMIT = 500000;
    private float balance;

    public HavakAccount(AccountData accountData) {
        super(accountData);
        this.balance = getBalance();
    }

    @Override
    protected boolean canWithdraw(int amount) {
        return getBalance() + OVERDRAFT_LIMIT >= amount;
    }

    public float doubleInterestCharge() {
        return balance = OVERDRAFT_LIMIT * 2;
    }
}