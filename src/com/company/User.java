package com.company;

public class User {
    private final String idNumber;
    private final String pin;
    private final Account salaryAccount;
    private final Account savingsAccount;

    public User(String idNumber, String pin, String salaryAccountNumber, String savingsAccountNumber) {
        this.idNumber = idNumber;
        this.pin = pin;
        this.salaryAccount = new Account("Salary", salaryAccountNumber, 0);
        this.savingsAccount = new Account("Savings", savingsAccountNumber, 0);
    }

    public String getPin() {
        return pin;
    }

    public void showAccounts() {
        System.out.println(salaryAccount);
        System.out.println(savingsAccount);
    }
    // TODO toAccount fattas
    public boolean transfer(String fromAccount, String toAccount, double amount) {
        Account from = fromAccount.equalsIgnoreCase("Salary") ? salaryAccount : savingsAccount;

        if (from.withdraw(amount)) {
            //Formatteringen hade jag totalt glömt bort, fick av AI
            System.out.printf("Amount %.2f has been deducted from %s.\\n", amount, from.getAccountNumber());
            return true;
        }
        return false;
    }

    // Kanske hade varit bättre med toLowerCase och jämfört strängar och varför gjorde jag en boolean av den, blev krångligt

    public boolean deposit(String accountType, double amount) {
        Account account = accountType.equalsIgnoreCase("Salary") ? salaryAccount : savingsAccount;

        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }
}

