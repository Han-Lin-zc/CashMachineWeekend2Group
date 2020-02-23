package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();
    private int id = 1000;

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Basic", "basic@gmail.com", 500
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Premium", "premium@gmail.com", 200
        )));

        accounts.put(3000, new SavingAccount(new AccountData(
                3000, "Saving", "saving@gmail.com", 300
        )));

        accounts.put(4000, new HavakAccount(new AccountData(
                4000, "Havak", "havak@gmail.com", 400
        )));
    }

    public void addNewAccount(String name, String email, float initialDeposit, String accountType){
        if(accountType.toLowerCase().equals("basic")){
            accounts.put(this.id, new BasicAccount(new AccountData(this.id, name, email, initialDeposit)));
            this.id++;
        }else if(accountType.toLowerCase().equals("premium")){
            accounts.put(this.id, new PremiumAccount(new AccountData(this.id, name, email, initialDeposit)));
            this.id++;
        }else if(accountType.toLowerCase().equals("savings")){
            accounts.put(this.id, new SavingAccount(new AccountData(this.id, name, email, initialDeposit)));
            this.id++;
        }else if(accountType.toLowerCase().equals("havak")){
            accounts.put(this.id, new HavakAccount(new AccountData(this.id, name, email, initialDeposit)));
            this.id++;
        }
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, float amount) {
        if (amount < 0) {
            return ActionResult.fail("Please enter a positive number");
        }
            Account account = accounts.get(accountData.getId());
            account.deposit(amount);


            return ActionResult.success(account.getAccountData());
        }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
