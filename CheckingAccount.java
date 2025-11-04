package project.project2;
//Ryan Tsui CS210 - Project 2
public class CheckingAccount extends BankAccount {
    private double overDraftLimit;
    public CheckingAccount(String accountNumber, String accountType, double balance) {
        super(accountNumber, accountType, balance);
        this.overDraftLimit = overDraftLimit;
    }

    public CheckingAccount(String accountNumber, String accountType, double balance, double interestRate) {
        super(accountNumber, accountType, balance);

    }

    @Override
    public boolean withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            return true;
        }
        return false;
    }
}
