package main.com.virtualbank.userInterface;

import main.com.virtualbank.model.AccountHolder;
import main.com.virtualbank.service.AccountService;
import main.com.virtualbank.service.AccountServiceImpl;

import java.util.concurrent.atomic.AtomicBoolean;

public class UserInterface {
    private static final Input input = new Input();
    private static AccountHolder loggedInAccount;
    private final AccountService BANK_SERVICE = new AccountServiceImpl();
    private final String exitConfirmation = "Are you sure you want to exit? Press y to continue... ";
    private final String loginString = "Kindly login to continue, type \"quit\" to cancel ...\n\n";
    private final String welcomeScreen = "1. Login\n2. Register\n3. Exit\n\n";
    private final String menu = "1. Deposit\n2. Withdraw\n3.Transfer\n4. Logout\n5.Exit\n\n";
    private final String amount = "Enter amount: ";
    private final String transferUsername = "Enter recipient's username: ";
    private final String error = "Something went wrong!\n\n";

    public void startUI() {
        AtomicBoolean loggedIn = new AtomicBoolean();
        startApp(loggedIn);

    }

    private void showWelcomeScreen() {
        String welcome = "\nWelcome to Virtual Bank 1.0 \n\n";
        System.out.println(welcome);
    }

    private AccountHolder startApp(AtomicBoolean loggedInStatus) {

        while (!loggedInStatus.get()) {
            clearScreen();
            showWelcomeScreen();
            try {
                int welcomeOption = input.getInt(welcomeScreen);

                switch (welcomeOption) {
                    case 1 -> loggedInAccount = login(loggedInStatus);
                    case 2 -> register();
                    case 3 -> exitApp();
                    case 4 -> loggedInStatus.set(true);
                    default -> System.out.println("Invalid Option selected!");
                }
            } catch (Exception error) {
                System.out.println("Only numbers are allowed!");
            }

        }
        return null;
    }

    private AccountHolder login(AtomicBoolean loggedInStatus) {
        boolean cancel = false;

        while (!loggedInStatus.get()) {
            clearScreen();
            showWelcomeScreen();
            System.out.println(welcomeScreen);
            System.out.println(loginString);

            String username = input.get("Enter username: ", true);
            if (username == null) return null;

            String password = input.get("Enter password: ");
            if (password == null) return null;

            AccountHolder accountHolder = BANK_SERVICE.login(username, password);

            if (accountHolder != null) {
                loggedInStatus.set(true);
                System.out.println("LOGGED IN SUCCESSFULLY!");
                pressAnyKeyToContinue();
                return accountHolder;
            }
            System.out.println(error);
        }
        return null;
    }

    private boolean checkForQuit(String string) {
        return string.equalsIgnoreCase("quit");
    }

    private void register() {
        System.out.print("Answer on-screen questions to register. Type \"quit\" anytime to cancel.\n\n");

        String username = input.get("Enter username: ", true, "");
        if (username == null) return;

        String password = input.get("Enter password: ", true, "");
        if (password == null) return;

        String firstName = input.get("Enter your first name: ", true, "");
        if (firstName == null) return;

        String lastName = input.get("Enter your last name: ", true, "");
        if (lastName == null) return;


        try {
            BANK_SERVICE.addAccountHolder(username, password, firstName, lastName);
            System.out.println("Successfully Created account!");
            pressAnyKeyToContinue();
        } catch (final Exception ignored) {
        }
    }

    private void exitApp() {
        System.out.println(exitConfirmation);
    }

    private void pressAnyKeyToContinue() {
        input.get("Press any key to continue ...");
    }

    public void clearScreen() {
        System.out.flush();
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception ignored) {
        }

    }
}
