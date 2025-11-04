package project.project2;
//Ryan Tsui CS210 - Project 2
public abstract class BankAccount {
    double balance;
    final String accountNumber;
    final String accountType;

    public BankAccount(String accountNumber, String accountType, double balance) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public abstract boolean withdraw(double amount);

    public String getAccountType(){
        return accountType;
    }
    public String getAccountNumber(){
        return accountNumber;
    }
}
