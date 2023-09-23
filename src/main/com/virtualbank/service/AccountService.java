package main.com.virtualbank.service;

import main.com.virtualbank.model.AccountHolder;

public interface AccountService {
    void addAccountHolder(String username, String password, String firstName, String lastName);

    void deposit(String username, String password, double amount);

    void withdraw(String username, String password, double amount);

    void transfer(String fromUsername, String password, String toUsername, double amount);

    double getAccountBalance(String name);

    AccountHolder login(String username, String password);
}
