package main.com.virtualbank.userInterface;

import main.com.virtualbank.exceptions.QuitException;

import java.util.Scanner;

public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public String get(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public String get(String prompt, boolean quitFlag) throws QuitException {
        String inputString = get(prompt);
        if (quitFlag && inputString.equalsIgnoreCase("quit")) {
            throw new QuitException();
        }
        return inputString;
    }

    public String get(String prompt, boolean quitFlag, String... notAllowedStrings) throws QuitException {
        while (true) {
            String inputString = get(prompt);

            if (quitFlag && inputString.equalsIgnoreCase("quit")) {
                throw new QuitException();
            }
            boolean isNotAllowed = false;
            for (String notAllowed : notAllowedStrings) {
                if (notAllowed.equalsIgnoreCase(inputString)) {
                    isNotAllowed = true;
                    break;
                }
            }

            if (!isNotAllowed) {
                return inputString;
            }
        }
    }

    public String get(String prompt, String... notAllowedStrings) {
        while (true) {
            System.out.print(prompt);
            String inputString = scanner.nextLine();

            boolean isNotAllowed = false;
            for (String notAllowed : notAllowedStrings) {
                if (notAllowed.equalsIgnoreCase(inputString)) {
                    isNotAllowed = true;
                    break;
                }
            }

            if (!isNotAllowed) {
                return inputString;
            }

            System.out.println("Invalid input. Please enter a different value. ");
        }
    }

    public int getInt(String prompt) throws QuitException {
        boolean validNumber = false;
        int number = 0;
        while (!validNumber) {
            try {
                String userInput = get(prompt, true);
                validNumber = true;
                number = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.print("Only numbers are allowed! ");
            }
        }
        return number;
    }


}
