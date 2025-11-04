# bankProject

# Bank Management System (Java)

This is project a project that I have worked on which is a console-based Bank Management System written in Java.  
It allows users to manage two different types of bank accounts (checking and savings) through a text-based menu.  
The program demonstrates object-oriented programming concepts such as inheritance, encapsulation, and polymorphism, and uses Java’s built-in Scanner class for user input.

## Features
- Create new checking or savings accounts
- Deposit money into an account
- Withdraw money from an account
- Check account balances
- Display all accounts
- Exit the program cleanly

## Program Structure

| Class | Description |
|--------|--------------|
| **Bank.java** | The main control class. Manages a collection of accounts, handles user input via Scanner, and provides the main menu interface. |
| **BankAccount.java** | The base class for all account types. Contains shared fields (account number, owner name, balance) and common methods like deposit(), withdraw(), and getBalance(). |
| **CheckingAccount.java** | A subclass of BankAccount that can include features like transaction limits or fees. Demonstrates inheritance. |
| **SavingsAccount.java** | A subclass of BankAccount that can include interest calculation or withdrawal restrictions. Demonstrates inheritance and method overriding. |

## Use of Scanner
The program uses Java’s Scanner class to read input from the console.  
The Scanner object is created once (typically in Main.java) and used to read user choices and account details.
