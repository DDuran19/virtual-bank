package main.com.virtualbank.service;

import main.com.virtualbank.model.AccountHolder;

public interface AccountService {
    void addAccountHolder(String username, String password, String firstName, String lastName);

    void deposit(String username, String password, double amount);

    void deposit(AccountHolder account, double amount);

    void withdraw(String username, String password, double amount);

    void withdraw(AccountHolder account, double amount);

    void transfer(String fromUsername, String password, String toUsername, double amount);

    void transfer(AccountHolder account, String toUsername, double amount);

    double getAccountBalance(String name);

    double getAccountBalance(AccountHolder account);

    void checkIfUserExists(String username);

    AccountHolder login(String username, String password);

    void showLogs(AccountHolder account);

    void authenticate(AccountHolder account, String password);
}
