package main.com.virtualbank.userInterface;

import main.com.virtualbank.exceptions.CredentialsException;
import main.com.virtualbank.exceptions.QuitException;
import main.com.virtualbank.exceptions.WithdrawException;
import main.com.virtualbank.model.AccountHolder;
import main.com.virtualbank.service.AccountService;

public class BankUI {

    private final AccountHolder account;
    private final AccountService BANK_SERVICE;
    private final Input input = new Input();
    private final Runnable clearScreen;

    public BankUI(AccountHolder account, AccountService BANK_SERVICE, Runnable clearScreen) {
        this.account = account;
        this.BANK_SERVICE = BANK_SERVICE;
        this.clearScreen = clearScreen;
    }


    public void start() throws QuitException {
        System.out.println("Welcome to the Virtual Bank!");

        while (true) {
            clearScreen.run();
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Check Balance");
            System.out.println("5. Show Transaction Logs");
            System.out.println("6. Logout");

            int choice = input.getInt("");
            try {
                switch (choice) {
                    case 1:
                        performDeposit();
                        break;
                    case 2:
                        performWithdraw();
                        break;
                    case 3:
                        performTransfer();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        showTransactionLogs();
                        break;
                    case 6:
                        new UserInterface().startUI();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again. ");
                }
            } catch (QuitException ignored) {
            } catch (CredentialsException ignored) {
                System.out.println("Unable to authenticate!");
            }
        }
    }

    private void performDeposit() throws QuitException {
        double depositAmount = input.getDouble("Enter Deposit Amount: ");
        BANK_SERVICE.deposit(account, depositAmount);
    }

    private void performWithdraw() throws QuitException, CredentialsException {
        authenticate();

        boolean validAmount = false;
        while (!validAmount) {
            try {
                double withdrawAmount = input.getDouble("Enter Withdraw Amount: ");
                BANK_SERVICE.withdraw(account, withdrawAmount);
                validAmount = true;
            } catch (WithdrawException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void performTransfer() throws QuitException, CredentialsException {
        authenticate();
        String toUsername = input.get("Enter receiving username: ");
        BANK_SERVICE.checkIfUserExists(toUsername);

        boolean validAmount = false;
        while (!validAmount) {
            try {
                double transferAmount = input.getDouble("Enter Withdraw Amount: ");
                BANK_SERVICE.transfer(account, toUsername, transferAmount);
                validAmount = true;
            } catch (WithdrawException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void checkBalance() throws QuitException {
        double currentBalance = BANK_SERVICE.getAccountBalance(account);
        System.out.println("Your current balance is " + currentBalance + ".");
    }

    private void showTransactionLogs() throws QuitException {
        BANK_SERVICE.showLogs(account);

    }

    private void authenticate() throws CredentialsException {
        System.out.println("Please authenticate to proceed with this transaction. ");
        String password = input.get("Enter password: ");
        BANK_SERVICE.authenticate(account, password);
    }
}

