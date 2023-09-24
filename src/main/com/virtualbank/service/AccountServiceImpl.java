package main.com.virtualbank.service;

import main.com.virtualbank.enums.CredentialErrorCodes;
import main.com.virtualbank.enums.TransactionTypes;
import main.com.virtualbank.exceptions.CredentialsException;
import main.com.virtualbank.exceptions.WithdrawException;
import main.com.virtualbank.model.AccountHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AccountServiceImpl implements AccountService {

    private final Map<String, AccountHolder> accountHolderMap = new HashMap<>();

    @Override
    public void addAccountHolder(String username, String password, String firstName, String lastName) {

        checkIfAccountExists(username);
        AccountHolder newAccountHolder = new AccountHolder(username, firstName, lastName, password);
        addToMemory(username, newAccountHolder);
    }

    @Override
    public void deposit(AccountHolder account, double amount) {
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);

        logTransaction(account, amount, currentBalance, newBalance, TransactionTypes.DEPOSIT);
    }

    public void deposit(String username, String password, double amount) {

        AccountHolder account = login(username, password);
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);

        logTransaction(account, amount, currentBalance, newBalance, TransactionTypes.DEPOSIT);
    }


    private void deposit(String toUsername, double amount, String from) {

        AccountHolder account = getAccountHolder(toUsername);
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + amount;
        account.setBalance(newBalance);
        logTransaction(account, amount, currentBalance, newBalance, from, toUsername);
    }

    @Override
    public void withdraw(String username, String password, double amount) {
        AccountHolder account = login(username, password);
        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new WithdrawException();
        }

        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);

        logTransaction(account, amount, currentBalance, newBalance, TransactionTypes.WITHDRAW);
    }

    public void withdraw(String username, String password, double amount, String to) {
        AccountHolder account = login(username, password);
        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new WithdrawException();
        }

        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);

        logTransaction(account, amount, currentBalance, newBalance, username, to);
    }

    @Override
    public void withdraw(AccountHolder account, double amount) {
        double currentBalance = account.getBalance();
        if (currentBalance < amount) {
            throw new WithdrawException();
        }

        double newBalance = currentBalance - amount;
        account.setBalance(newBalance);

        logTransaction(account, amount, currentBalance, newBalance, TransactionTypes.WITHDRAW);
    }

    public void withdraw(AccountHolder account, double amount, boolean transferFlag) {
        if (transferFlag) {
            double currentBalance = account.getBalance();
            if (currentBalance < amount) {
                throw new WithdrawException();
            }

            double newBalance = currentBalance - amount;
            account.setBalance(newBalance);
            return;
        }
        withdraw(account, amount);
    }

    @Override
    public void showLogs(AccountHolder account) {
        for (TransactionLog log : account.getLogs()) {
            System.out.println(log.toString());
        }
    }

    private void logTransaction(AccountHolder account, double amount, double currentBalance, double newBalance, TransactionTypes type) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logDetails = amount + "⮚ self" + "\t" + currentBalance + " ⟶ " + newBalance;
        TransactionLog newLog = new TransactionLog(
                type,
                timestamp,
                logDetails);

        account.updateLogs(newLog);
    }

    private void logTransaction(AccountHolder account, double amount, double currentBalance, double newBalance, String from, String to) {
        LocalDateTime timestamp = LocalDateTime.now();
        String logDetails = amount + "⮚ " + from + " to " + to + " \t" + currentBalance + " ⟶ " + newBalance;
        TransactionLog newLog = new TransactionLog(
                TransactionTypes.TRANSFER,
                timestamp,
                logDetails);

        account.updateLogs(newLog);
    }

    @Override
    public void transfer(String fromUsername, String password, String toUsername, double amount) {
        withdraw(fromUsername, password, amount, toUsername);
        deposit(toUsername, amount, fromUsername);
    }

    @Override
    public void transfer(AccountHolder account, String toUsername, double amount) throws WithdrawException {
        withdraw(account, amount, true);
        deposit(toUsername, amount, account.getUsername());
    }

    @Override
    public double getAccountBalance(String name) {
        return getAccountHolder(name).getBalance();
    }

    public double getAccountBalance(AccountHolder account) {
        return account.getBalance();
    }

    @Override
    public void checkIfUserExists(String username) throws CredentialsException {
        AccountHolder account = accountHolderMap.get(username);
        if (account == null) throw new CredentialsException(CredentialErrorCodes.ACCOUNT_DOES_NOT_EXIST);
    }

    @Override
    public AccountHolder login(String username, String password) throws CredentialsException {
        AccountHolder account = accountHolderMap.get(username);
        LocalDateTime timestamp = LocalDateTime.now();
        String logDetails = "";

        if (account == null) {
            throw new CredentialsException(CredentialErrorCodes.ACCOUNT_DOES_NOT_EXIST);
        }

        try {
            if (account.passwordIsWrong(password)) {
                logDetails = "Invalid Password - " + password;
                throw new CredentialsException(CredentialErrorCodes.INVALID_PASSWORD);
            }
            logDetails = "Successful Login!";
            return account;
        } finally {
            TransactionLog newLog = new TransactionLog(
                    TransactionTypes.LOGIN_ATTEMPT,
                    timestamp,
                    logDetails);

            account.updateLogs(newLog);
        }
    }

    public void authenticate(AccountHolder account, String password) throws CredentialsException {
        if (account.passwordIsWrong(password)) throw new CredentialsException(CredentialErrorCodes.INVALID_PASSWORD);
    }

    @Override
    public String getFullName(AccountHolder account) {
        return account.getFullName();
    }

    private AccountHolder getAccountHolder(String name) {
        return accountHolderMap.get(name);
    }

    private void checkIfAccountExists(String username) {
        if (accountHolderMap.containsKey(username)) {
            throw new CredentialsException(CredentialErrorCodes.ACCOUNT_EXISTS);
        }
    }

    private void addToMemory(String username, AccountHolder accountHolder) {
        accountHolderMap.put(username, accountHolder);
    }
}
