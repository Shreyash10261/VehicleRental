package com.utils;

import java.util.Scanner;

// Utility class for handling and validating user input
public class InputValidator {
    // Shared scanner for all input operations
    private static Scanner scanner = new Scanner(System.in);

    // Prompts user for string input and returns trimmed value
    public static String getStringInput(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine().trim();
    }

    // Prompts user for integer input with validation
    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(" Invalid input. Please enter a number.");
            }
        }
    }
}