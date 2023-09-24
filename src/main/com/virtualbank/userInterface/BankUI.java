package main.com.virtualbank.userInterface;

import main.com.virtualbank.exceptions.QuitException;
import main.com.virtualbank.model.AccountHolder;

public class BankUI {

    private final AccountHolder account;
    private final Input input = new Input();

    public BankUI(AccountHolder account) {
        this.account = account;
    }

    public void start() throws QuitException {
        System.out.println("Welcome to the Virtual Bank!");

        while (true) {
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
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again. ");
                }
            } catch (QuitException ignored) {
            }
        }
    }

    private void performDeposit() throws QuitException {
        double currentBalance = account.getBalance();
        double newBalance = currentBalance + input.getDouble("Enter Deposit Amount: ");
        account.setBalance(newBalance);
    }

    private void performWithdraw() throws QuitException {
        boolean amountIsInvalid = true;
        double currentBalance = account.getBalance();
        double newBalance = currentBalance;

        while (amountIsInvalid) {
            newBalance = currentBalance - input.getDouble("Enter Withdraw Amount: ");
            if (newBalance > -1) {
                amountIsInvalid = false;
            }
        }
        account.setBalance(newBalance);

    }

    private void performTransfer() throws QuitException {
        // Implement logic to perform a transfer for the logged-in user.
        System.out.println("performTransfer");
    }

    private void checkBalance() throws QuitException {
        // Implement logic to check and display the account balance for the logged-in user.
        System.out.println("checkBalance");

    }

    private void showTransactionLogs() throws QuitException {
        // Implement logic to display transaction logs for the logged-in user.
        System.out.println("showTransactionLogs");

    }
}

