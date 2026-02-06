package org.example;

/**
 * Children's Savings Account - demonstrates Inheritance and Polymorphism
 * Special features for kids: rewards for savings, withdrawal limit
 */
public class ChildrenSavingsAccount extends BankAccount {
    // Additional fields specific to children's account
    private int age;
    private double rewardPoints;
    private static final double WITHDRAWAL_LIMIT = 50.0; // Kids can only withdraw $50 at a time
    private static final double REWARD_RATE = 0.1; // 10% of deposit as reward points

    // Constructor - calls parent constructor
    public ChildrenSavingsAccount(String accountNumber, String accountHolderName, double initialBalance, int age) {
        super(accountNumber, accountHolderName, initialBalance);
        this.age = age;
        this.rewardPoints = 0;
    }

    // Getter for age
    public int getAge() {
        return age;
    }

    // Getter for reward points
    public double getRewardPoints() {
        return rewardPoints;
    }

    // Override deposit to add reward points - Polymorphism
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
        if (amount > 0) {
            double pointsEarned = amount * REWARD_RATE;
            rewardPoints += pointsEarned;
            System.out.println("🌟 Great job saving! You earned " + String.format("%.2f", pointsEarned) + " reward points!");
        }
    }

    // Override withdraw with limit for children - Polymorphism
    @Override
    public boolean withdraw(double amount) {
        if (amount > WITHDRAWAL_LIMIT) {
            System.out.println("⚠️ Kids can only withdraw up to $" + WITHDRAWAL_LIMIT + " at a time.");
            System.out.println("Ask a parent/guardian for larger withdrawals!");
            return false;
        }
        return super.withdraw(amount);
    }

    // Additional method - Redeem reward points
    public void redeemRewardPoints() {
        if (rewardPoints >= 10) {
            System.out.println("🎁 You have " + String.format("%.2f", rewardPoints) + " reward points!");
            System.out.println("Keep saving to earn more rewards!");
        } else {
            System.out.println("You need at least 10 points to redeem. Current points: " + String.format("%.2f", rewardPoints));
        }
    }

    // Override display to show children-specific info
    @Override
    public void displayAccountInfo() {
        super.displayAccountInfo();
        System.out.println("Age: " + age + " years");
        System.out.println("Reward Points: " + String.format("%.2f", rewardPoints) + " 🌟");
        System.out.println("================================");
    }
}
