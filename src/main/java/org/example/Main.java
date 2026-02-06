package org.example;

import java.util.Scanner;

/**
 * Main class - Entry point for Children Bank Application
 * Demonstrates the use of OOP concepts: Encapsulation, Inheritance, Polymorphism
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   🏦 WELCOME TO CHILDREN'S BANK! 🏦   ║");
        System.out.println("╚════════════════════════════════════════╝");

        // Get user information
        System.out.print("\nEnter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        // Choose account type
        System.out.println("\n===== SELECT ACCOUNT TYPE =====");
        System.out.println("1. Savings Account (Earn reward points, $50 withdrawal limit)");
        System.out.println("2. Checking Account (Track transactions, set monthly allowance)");
        System.out.print("Choose account type: ");
        int accountType = scanner.nextInt();

        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = scanner.nextDouble();

        // Create account with auto-generated account number - Polymorphism
        String accountNumber;
        BankAccount account; // Using parent class reference - Polymorphism

        if (accountType == 1) {
            accountNumber = "SAV" + System.currentTimeMillis() % 10000;
            account = new ChildrenSavingsAccount(accountNumber, name, initialDeposit, age);
            System.out.println("\n🎉 Savings Account created successfully!");
        } else {
            accountNumber = "CHK" + System.currentTimeMillis() % 10000;
            account = new ChildrenCheckingAccount(accountNumber, name, initialDeposit, age);
            System.out.println("\n🎉 Checking Account created successfully!");
        }

        account.displayAccountInfo();

        // Main menu loop
        boolean running = true;
        while (running) {
            displayMenu(account);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: $");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 3:
                    System.out.println("\n💰 Current Balance: $" + String.format("%.2f", account.getBalance()));
                    break;

                case 4:
                    handleSpecialFeature(account);
                    break;

                case 5:
                    account.displayAccountInfo();
                    break;

                case 6:
                    System.out.println("\n👋 Thank you for using Children's Bank!");
                    System.out.println("Keep saving for a bright future! 🌟");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Display menu based on account type
    private static void displayMenu(BankAccount account) {
        System.out.println("\n===== MENU =====");
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");

        if (account instanceof ChildrenSavingsAccount) {
            System.out.println("4. View Reward Points");
        } else if (account instanceof ChildrenCheckingAccount) {
            System.out.println("4. Special Features (Allowance & Transactions)");
        }

        System.out.println("5. View Account Info");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    // Handle special features based on account type
    private static void handleSpecialFeature(BankAccount account) {
        Scanner scanner = new Scanner(System.in);

        if (account instanceof ChildrenSavingsAccount) {
            ChildrenSavingsAccount savingsAccount = (ChildrenSavingsAccount) account;
            savingsAccount.redeemRewardPoints();
        } else if (account instanceof ChildrenCheckingAccount) {
            ChildrenCheckingAccount checkingAccount = (ChildrenCheckingAccount) account;

            System.out.println("\n--- Checking Account Features ---");
            System.out.println("1. Set Monthly Allowance");
            System.out.println("2. Add Monthly Allowance to Balance");
            System.out.println("3. Reset Monthly Transaction Count");
            System.out.print("Choose an option: ");
            int subChoice = scanner.nextInt();

            switch (subChoice) {
                case 1:
                    System.out.print("Enter monthly allowance amount: $");
                    double allowance = scanner.nextDouble();
                    checkingAccount.setMonthlyAllowance(allowance);
                    break;
                case 2:
                    checkingAccount.addMonthlyAllowance();
                    break;
                case 3:
                    checkingAccount.resetMonthlyTransactions();
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}