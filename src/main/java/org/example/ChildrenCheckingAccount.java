package org.example;

/**
 * Children's Checking Account - demonstrates Inheritance and Polymorphism
 * Special features: transaction tracking, monthly allowance feature, no withdrawal limit
 */
public class ChildrenCheckingAccount extends BankAccount {
    // Additional fields specific to children's checking account
    private int age;
    private int transactionCount;
    private double monthlyAllowance;
    private static final int FREE_TRANSACTIONS = 5; // First 5 transactions per month are free
    private static final double TRANSACTION_FEE = 1.0; // $1 fee after free transactions

    // Constructor - calls parent constructor
    public ChildrenCheckingAccount(String accountNumber, String accountHolderName, double initialBalance, int age) {
        super(accountNumber, accountHolderName, initialBalance);
        this.age = age;
        this.transactionCount = 0;
        this.monthlyAllowance = 0;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Getter for transaction count
    public int getTransactionCount() {
        return transactionCount;
    }

    // Set monthly allowance
    public void setMonthlyAllowance(double amount) {
        if (amount >= 0) {
            this.monthlyAllowance = amount;
            System.out.println("✅ Monthly allowance set to: $" + String.format("%.2f", amount));
        } else {
            System.out.println("Invalid allowance amount!");
        }
    }

    // Add monthly allowance
    public void addMonthlyAllowance() {
        if (monthlyAllowance > 0) {
            super.deposit(monthlyAllowance);
            System.out.println("💵 Monthly allowance of $" + String.format("%.2f", monthlyAllowance) + " has been added!");
        } else {
            System.out.println("No monthly allowance is set.");
        }
    }

    // Override deposit with transaction tracking - Polymorphism
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (amount > 0) {
            transactionCount++;
            checkTransactionFee();
        }
    }

    // Override withdraw with transaction tracking - Polymorphism
    @Override
    public boolean withdraw(double amount) {
        boolean success = super.withdraw(amount);
        if (success) {
            transactionCount++;
            checkTransactionFee();
        }
        return success;
    }

    // Check and apply transaction fee if needed
    private void checkTransactionFee() {
        if (transactionCount > FREE_TRANSACTIONS) {
            if (getBalance() >= TRANSACTION_FEE) {
                super.withdraw(TRANSACTION_FEE);
                System.out.println("⚠️ Transaction fee applied: $" + TRANSACTION_FEE);
                System.out.println("(You have used " + transactionCount + " transactions. First " + FREE_TRANSACTIONS + " are free)");
            }
        } else {
            System.out.println("📊 Transaction " + transactionCount + " of " + FREE_TRANSACTIONS + " free transactions used.");
        }
    }

    // Reset monthly transaction count
    public void resetMonthlyTransactions() {
        transactionCount = 0;
        System.out.println("🔄 Monthly transaction count reset!");
        System.out.println("You have " + FREE_TRANSACTIONS + " free transactions this month.");
    }

    // Override display to show checking-specific info
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Age: " + age + " years");
        System.out.println("Transaction Count: " + transactionCount);
        System.out.println("Monthly Allowance: $" + String.format("%.2f", monthlyAllowance));
        System.out.println("Free Transactions Left: " + Math.max(0, FREE_TRANSACTIONS - transactionCount));
        System.out.println("================================");
    }
}
