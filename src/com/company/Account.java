package com.company;

public class Account {
    private final String type;
    private final String accountNumber;
    private double balance;

    public Account(String type, String accountNumber, double balance) {
        this.type = type;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Unsufficient funds.");
            return false;
        }
        balance -= amount;
        return true;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return type + "account (" + accountNumber + "): " + balance + " sek";
    }
}

