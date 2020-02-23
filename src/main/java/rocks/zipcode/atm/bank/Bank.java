package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Basic", "basic@gmail.com", 500, "Basic"
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Premium", "premium@gmail.com", 200, "Premium"
        )));

        accounts.put(3000, new SavingAccount(new AccountData(
                3000, "Saving", "saving@gmail.com", 300, "Saving"
        )));

        accounts.put(4000, new HavakAccount(new AccountData(
                4000, "Havak", "havak@gmail.com", 400, "havak"
        )));
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
            String balance = String.format("%1$.2f", account.getBalance());
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + balance);
        }
    }

    public int getNewId() {
        return accounts.size()+1000;
    }

    public boolean checkId(int idToCheck){
        for (int key : accounts.keySet()) {
            if(key == idToCheck){
                return false;
            }
        }
        return true;
    }


    public void addAccount(String name, String email, int deposit, String accountType,int newId){
//Need to create logic to check for duplicate IDs

        if(accountType.equalsIgnoreCase("Basic")){
            accounts.put(newId, new BasicAccount(new AccountData(
                    newId, name, email, deposit, accountType
            )));
        } else if (accountType.equalsIgnoreCase("Premium")){
            accounts.put(newId, new PremiumAccount(new AccountData(
                    newId, name, email, deposit, accountType
            )));
        } else if(accountType.equalsIgnoreCase("Savings")){
            accounts.put(newId, new SavingAccount(new AccountData(
                    newId, name, email, deposit, accountType
            )));
        } else {
            accounts.put(newId, new HavakAccount(new AccountData(
                    newId, name, email, deposit, accountType
            )));
        }
    }
}
