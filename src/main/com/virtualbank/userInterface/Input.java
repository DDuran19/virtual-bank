package main.com.virtualbank.userInterface;

import java.util.Scanner;

public class Input {
    private final Scanner scanner = new Scanner(System.in);

    public String get(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }

    public String get(String prompt, boolean quitFlag) {
        String inputString = get(prompt);
        if (quitFlag && inputString.equalsIgnoreCase("quit")) {
            return null;
        }
        return inputString;
    }

    public String get(String prompt, boolean quitFlag, String... notAllowedStrings) {
        while (true) {
            String inputString = get(prompt);

            if (quitFlag && inputString.equalsIgnoreCase("quit")) {
                return null;
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

            System.out.println("Invalid input. Please enter a different value.");
        }
    }

    public int getInt(String prompt) {
        System.out.println(prompt);
        return scanner.nextInt();
    }


}
