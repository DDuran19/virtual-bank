package main.com.virtualbank.model;

import main.com.virtualbank.service.TransactionLog;

import java.util.ArrayList;


public class AccountHolder {
    private final String username;
    private final ArrayList<TransactionLog> logs = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String password;
    private double balance;

    public AccountHolder(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean passwordIsWrong(String password) {
        return !this.password.equals(password);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void updateLogs(TransactionLog newLog) {
        this.logs.add(newLog);
    }

    public ArrayList<TransactionLog> getLogs() {
        return logs;
    }

    public String getUsername() {
        return this.username;
    }
}