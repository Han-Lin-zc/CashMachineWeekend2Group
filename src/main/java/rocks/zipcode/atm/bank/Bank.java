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

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public void addAccount(String name, String email, int deposit, String accountType){

        int generateId = getRandomNumberInRange(1000,8000);

//Need to create logic to check for duplicate IDs

        if(accountType.equalsIgnoreCase("Basic")){
            accounts.put(generateId, new BasicAccount(new AccountData(
                    generateId, name, email, deposit
            )));
        } else if (accountType.equalsIgnoreCase("Premium")){
            accounts.put(generateId, new PremiumAccount(new AccountData(
                    generateId, name, email, deposit
            )));
        } else if(accountType.equalsIgnoreCase("Savings")){
            accounts.put(generateId, new SavingAccount(new AccountData(
                    generateId, name, email, deposit
            )));
        } else {
            accounts.put(generateId, new HavakAccount(new AccountData(
                    generateId, name, email, deposit
            )));
        }
    }
}
