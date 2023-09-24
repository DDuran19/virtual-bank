package main.com.virtualbank;

import main.com.virtualbank.userInterface.UserInterface;

public class Main {
    public static void main(String[] args) {
        // TODO: Create a CLI for this application ✓
        // List All Account Holders and their balances x
        // Add Support for First Name and Last Name ✓
        // Add Support for Transactions - logs ✓

        UserInterface userInterface = new UserInterface();

        userInterface.startUI();
    }
}