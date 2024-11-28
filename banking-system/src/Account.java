import java.util.ArrayList;
import java.util.List;

public class Account {
    private double balance;
    private String email;
    private String name;
    private String tier;

    private List<Observer> observers;

    private static final double SILVER_THRESHOLD = 0;
    private static final double GOLD_THRESHOLD = 80000;
    private static final double PLATINUM_THRESHOLD = 100000;

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
        this.balance = 0.0;
        this.tier = "SILVER"; // Default tier
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than zero");
            return;
        }
        this.balance += amount;
        checkTierChange();
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than zero");
            return;
        }
        if (amount > this.balance) {
            System.out.println("Insufficient balance for withdrawal");
            return;
        }
        this.balance -= amount;
        checkTierChange();
    }

    private void checkTierChange() {
        String newTier;

        if (this.balance >= PLATINUM_THRESHOLD) {
            newTier = "PLATINUM";
        } else if (this.balance >= GOLD_THRESHOLD) {
            newTier = "GOLD";
        } else {
            newTier = "SILVER";
        }

        if (!newTier.equals(this.tier)) {
            this.tier = newTier;
            notifyObservers();
        }
    }

    private void notifyObservers() {
        String subject = "Your account tier has changed";
        String message = String.format("Hello %s,Your account has been upgraded to the %s tier based on your current balance of $%.2f.",
                this.name, this.tier, this.balance);
        observers.forEach(observer -> observer.updateCustomer(this.name,subject,message));
    }

    public double getBalance() {
        return balance;
    }

    public String getTier() {
        return tier;
    }
}
