package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String symbols = "0123456789abcdefghijklmnopqrstuvwxyz";


        System.out.println("Input the length of the secret code:");
        int numDigits;
        if (scanner.hasNextInt()) {
            numDigits = scanner.nextInt();
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", scanner.nextLine());
            return;
        }
        if (numDigits > 36 || numDigits <= 0) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique symbols.%n", numDigits);
            return;
        }

        System.out.println("Input the number of possible symbols in the code:");
        int numSymbols;
        if (scanner.hasNextInt()) {
            numSymbols = scanner.nextInt();
        } else {
            System.out.printf("Error: \"%s\" isn't a valid number.%n", scanner.nextLine());
            return;
        }
        if (numSymbols > 36 || numSymbols <= 0) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return;
        } else if (numDigits > numSymbols) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n", numDigits, numSymbols);
            return;
        }

        StringBuilder secretCode = generateSecretCode(numDigits, numSymbols, symbols);
        StringBuilder redactedCode = new StringBuilder();
        for (char symbol : secretCode.toString().toCharArray()) {
            redactedCode.append('*');
        }
        System.out.printf("The secret is prepared: %s (0-9, a-%s).%n", redactedCode, symbols.charAt(numSymbols - 1));

        System.out.println("Okay, let's start a game!");

        int turn = 1;
        while (true) {
            System.out.printf("Turn %d:%n", turn);

            // Get user input
            String guess = scanner.nextLine();

            // Grade its bulls and cows
            int bulls = gradeGuess(secretCode.toString(), guess);

            // If the number of bulls is the length of the secret code, user has won
            if (bulls == secretCode.length()) {
                System.out.println("Congratulations! You guessed the secret code.");
                return;
            }

            turn++;
        }
    }

    private static StringBuilder generateSecretCode(int length, int numSymbols, String symbols) {
        Random random = new Random();
        StringBuilder secretCode = new StringBuilder();

        while (secretCode.length() < length) {
            while (true) {
                int randIndex = random.nextInt(numSymbols);
                if (secretCode.indexOf(String.valueOf(symbols.charAt(randIndex))) == -1) {
                    secretCode.append(symbols.charAt(randIndex));
                    break;
                }
            }
        }

        return secretCode;
    }

    private static int gradeGuess(String code, String guess) {
        int cows = 0;
        int bulls = 0;

        for (int i = 0; i < guess.length(); i++) {
            // check if letter in guess is in code
            char guessLetter = guess.charAt(i);
            char codeLetter = code.charAt(i);

            if (guessLetter == codeLetter) {
                bulls++;
            } else if (code.contains(String.valueOf(guessLetter))) {
                cows++;
            }
        }

        if (bulls > 0 && cows > 0) {
            System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bulls, cows);
        } else if (bulls > 0) {
            System.out.printf("Grade: %d bull(s).%n", bulls);
        } else if (cows > 0) {
            System.out.printf("Grade: %d cow(s).%n", cows);
        }else {
            System.out.println("Grade: None.");
        }

        return bulls;
    }
}
