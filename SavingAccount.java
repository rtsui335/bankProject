package project.project2;
//Ryan Tsui CS210 - Project 2
class SavingAccount extends BankAccount{
    private static final double INTEREST_RATE = 2.5;
    public SavingAccount(String accountNumber, String accountType, double balance){
        super(accountNumber, accountType, balance);
    }
    @Override
    public boolean withdraw(double amount){
        if(amount > 0 && amount <= balance){
            balance -= amount;
            applyInterest();
            return true;
        }
        return false;
    }
    public void applyInterest(){
        balance = balance * (INTEREST_RATE / 100) + balance;
    }
}
