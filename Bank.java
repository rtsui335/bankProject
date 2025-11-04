//Ryan Tsui CS210 - Project 2
package project.project2;
import java.util.*;
public class Bank {
    private static final int maxAccounts = 10;
    private BankAccount[] accounts = new BankAccount[maxAccounts];
    private CheckingAccount[] checkingAccounts = new CheckingAccount[maxAccounts];
    private SavingAccount[] savingsAccounts = new SavingAccount[maxAccounts];
    private int accountCount = 1;
    private Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Bank bank = new Bank();

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Display All Accounts");
            System.out.println("5. ApplyInterestRateToAccounts");
            System.out.println("6. Exit");
            System.out.println("Choose an option: ");

            if (!bank.input.hasNextInt()) {
                System.out.println("Invalid option!");
                bank.input.nextLine();
                continue;
            }

            int userChoice = bank.input.nextInt();
            bank.input.nextLine();

            switch (userChoice) {
                case 1:
                    bank.createAccount();
                    break;
                case 2:
                    bank.deposit();
                    break;

                case 3:
                    bank.withdraw();
                    break;

                case 4:
                    bank.displayAllAccounts();
                    break;

                case 5:
                    bank.applyInterestRateToAccounts();
                    break;

                case 6:
                    bank.exit();
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }
    }
    private void createAccount() {
        if (accountCount > maxAccounts) {
            System.out.println("Bank account limit reached!");
            return;
        }

        String accountNumber = "AC" + accountCount;
        System.out.print("Enter 'S' for Savings or 'C' for Checking: ");
        String accountType = input.next().toUpperCase();
        input.nextLine();

        if (!accountType.equals("S") && !accountType.equals("C")) {
            System.out.println("Invalid account type.");
            return;
        }

        System.out.print("Enter initial balance: ");
        double balance = input.nextDouble();
        input.nextLine();

        if (balance <= 0){
            System.out.println("Failed to create account. Must deposit more than $0.");
            return;
        }

        if (accountType.equals("S")) {
            SavingAccount savings = new SavingAccount(accountNumber, "Saving", balance);
            accounts[accountCount - 1] = savings;
            savingsAccounts[accountCount - 1] = savings;
            System.out.println("Account created! Account Number: " + accountNumber + ", Balance: " + balance);
        } else {
            System.out.print("Enter overdraft limit: ");
            double overdraftLimit = input.nextDouble();
            input.nextLine();

            CheckingAccount checking = new CheckingAccount(accountNumber, "Checking", balance, overdraftLimit);
            accounts[accountCount - 1] = checking;
            checkingAccounts[accountCount - 1] = checking;
            System.out.println("Account created! Account Number: " + accountNumber + ", Balance: " + balance);
        }

        accountCount++;
    }

    private void deposit() {
        System.out.println("Enter account number: ");
        String accountNumber = input.nextLine();

        for (int i = 0; i < accountCount - 1; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber)) {
                System.out.println("Enter deposit amount: ");
                double amount = input.nextDouble();
                input.nextLine();

                if (amount <= 0) {
                    System.out.println("Failed Deposit - Must deposit more than $0, Account Balance: " + accounts[i].getBalance());
                    return;
                }

                accounts[i].deposit(amount);
                System.out.println("Successful Deposit, Account Balance: " + accounts[i].getBalance());
                return;
            }
        }
        System.out.println("Account not found!");
    }
    //need to fix bug where loop wont run
    private void withdraw() {
        System.out.println("Enter account number:");
        String accountNumber = input.nextLine();

        for (int i = 0; i < accountCount - 1; i++) {
            if (accounts[i] != null && accounts[i].getAccountNumber().equals(accountNumber)) {
                System.out.println("Enter withdraw amount:");
                double amount = input.nextDouble();
                input.nextLine();

                if (amount <= 0) {
                    System.out.println("Failed Withdrawal - Must withdraw more than $0. Account Balance: " + accounts[i].getBalance());
                    return;
                }

                boolean withdrawalSuccess = accounts[i].withdraw(amount);
                if (withdrawalSuccess) {
                    System.out.println("Successful Withdrawal, Account Balance: " + accounts[i].getBalance());
                } else if (accounts[i] instanceof CheckingAccount && accounts[i].getBalance() < 0) {
                    System.out.println("Failed Withdrawal, Account Balance Below Zero, Account Balance: " + accounts[i].getBalance());
                } else {
                    System.out.println("Failed Withdrawal, Insufficient " + accounts[i].getAccountType() + " Balance, Account Balance: " + accounts[i].getBalance());
                }
                return;
            }
        }
        System.out.println("Account not found!");
    }

    private void displayAllAccounts(){
        if(accountCount == 0){
            System.out.println("No accounts found!");
            return;
        }
        System.out.println("\nAccounts:");
        for(int i = 0; i < accountCount; i++){
            if(accounts[i] != null) {
                System.out.println("Account Number: " + accounts[i].getAccountNumber() + ", Type: " + accounts[i].getAccountType() + ", Balance: " + accounts[i].getBalance());
            }
        }
    }
    //need to fix
    private void applyInterestRateToAccounts(){
        //write for loop and iterate over accounts instanceof to make sure accounts in savingsAccounts
        boolean hasInterest = false;
        for(int i = 0; i < maxAccounts; i++) {
            if (accounts[i] != null && accounts[i] instanceof SavingAccount) {
                ((SavingAccount) accounts[i]).applyInterest();
                System.out.println("Interest applied to account " + accounts[i].getAccountNumber());
                hasInterest = true;
            }
        }

        if(hasInterest){
            System.out.println("Interest rate applied to all accounts.");
        } else {
            System.out.println("No accounts with interest rates to apply");
        }

    }

    private void exit() {
        System.out.println("Thank you for using our bank!");
        System.exit(0);
        input.close();
    }

    public CheckingAccount[] getCheckingAccounts() {
        return checkingAccounts;
    }

    public void setCheckingAccounts(CheckingAccount[] checkingAccounts) {
        this.checkingAccounts = checkingAccounts;
    }

    public SavingAccount[] getSavingsAccounts() {
        return savingsAccounts;
    }

    public void setSavingsAccounts(SavingAccount[] savingsAccounts) {
        this.savingsAccounts = savingsAccounts;
    }

    public BankAccount[] getAccounts() {
        return accounts;
    }

    public void setAccounts(BankAccount[] accounts) {
        this.accounts = accounts;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }
}

