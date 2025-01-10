package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // TODO flytta ut kod ur main för mer OOP om jag hinner. Lägga till exceptions!
    // Lade till deposit eftersom jag skapar mina konton med saldo 0:- så det första man måste göra efter skapat konto är en insättning

    static Scanner scanner = new Scanner(System.in);
    static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Create User");
            System.out.println("2. Log In");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> createUser();
                case 2 -> login();
                case 3 -> {
                    System.out.println("Exiting the program.");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    // TODO fixa validering för 10 nummer och PIN 4 nummer

    private static void createUser() {
        System.out.println("\n--- Create User ---");
        System.out.print("Enter your ID number (10 digits): ");
        String idNumber = scanner.nextLine();

        if (users.containsKey(idNumber)) {
            System.out.println("A user with this ID number already exists.");
            return;
        }

        System.out.print("Enter a PIN (4 digits): ");
        String pin = scanner.nextLine();

        String salaryAccount  = generateAccountNumber();
        String savingsAccount  = generateAccountNumber();

        users.put(idNumber, new User(idNumber, pin, salaryAccount , savingsAccount ));
        System.out.println("User created! Salary and savings accounts have been generated.");
    }

    private static void login() {
        System.out.println("\n--- Log In ---");
        System.out.print("Enter idNumber: ");
        String idNumber = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = users.get(idNumber);
        if (user != null && user.getPin().equals(pin)) {
            System.out.println("Login successful!");
            userMenu(user);
        } else {
            System.out.println("Incorrect idNumber or PIN.");
        }
    }

    private static void userMenu(User user) {
        while (true) {
            System.out.println("\n--- My Accounts ---");
            System.out.println("1. Show Accounts");
            System.out.println("2. Transfer");
            System.out.println("3. Deposit");
            System.out.println("4. Log Out");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> user.showAccounts();
                case 2 -> makeTransfer(user);
                case 3 -> deposit(user);
                case 4 -> {
                    System.out.println("You are now logged out.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
    // TODO fixa transfer, det dras från kontot men sätts inte in på andra kontot

    private static void makeTransfer(User user) {
        System.out.println("\n--- Make a Transfer ---");
        System.out.print("Choose the source account (Salary/Savings): ");
        String fromAccount = scanner.nextLine();

        System.out.print("Enter the recipient's account number: ");
        String toAccount = scanner.nextLine();

        System.out.print("Enter the amount: ");
        double amount = Double.parseDouble(scanner.nextLine());

        boolean success = user.transfer(fromAccount, toAccount, amount);
        if (success) {
            System.out.println("Transfer successful!");
        } else {
            System.out.println("Transfer failed. Check the balance or account details.");
        }
    }

    private static void deposit(User user) {
        System.out.println("\n--- Deposit Money ---");
        System.out.print("Choose the account (Salary/Savings): ");
        String accountType = scanner.nextLine();

        System.out.print("Enter the amount to deposit: ");
        double amount = Double.parseDouble(scanner.nextLine());

        boolean success = user.deposit(accountType, amount);
        if (success) {
            System.out.println("Money has been deposited into the account.");
        } else {
            System.out.println("Invalid account. Try again.");
        }
    }

    private static String generateAccountNumber() {
        return String.valueOf((int) (Math.random() * 1000000000));
    }
}

