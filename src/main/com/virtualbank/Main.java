package main.com.virtualbank;

import main.com.virtualbank.userInterface.UserInterface;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // TODO: Create a CLI for this application
        // List All Account Holders and their balances
        // Add Support for First Name and Last Name
        // Add Support for Transactions - logs

        UserInterface userInterface = new UserInterface();

        userInterface.startUI();
    }
}

// "F:\Program Files\jdk-17.0.2\bin\java.exe" "-javaagent:F:\Program Files\JetBrains\IntelliJ IDEA Community Edition
// 2023.2.2\lib\idea_rt.jar=53806:F:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.2.2\bin"
// -Dfile.encoding=UTF-8 -classpath F:\PC-Libraries\Documents\Coding\Java\learningJava\hello-world\out\production\virtual-bank;
// C:\Users\denve\.m2\repository\com\bluecatcode\junit\junit-4.12-extended\1.0.3\junit-4.12-extended-1.0.3.jar;
// C:\Users\denve\.m2\repository\junit\junit\4.12\junit-4.12.jar;C:\Users\denve\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;
// C:\Users\denve\.m2\repository\org\jetbrains\annotations\24.0.0\annotations-24.0.0.jar main.com.virtualbank.Main