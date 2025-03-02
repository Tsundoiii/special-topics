package gavinchen;

import java.io.*;
import java.util.Scanner;

public class StateSurvey {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to our survey. You may "
                    + "enter \"quit\" at any time to cancel the survey.");
            int age = getAge();
            String[][] states = readStateFile();
            String state = getState(states);
            int ZIPCode = getZIPCode();
            System.out.printf("\nAge:\t\t%d\n", age);
            System.out.printf("Address:\t%s %s\n\n", ZIPCode, state);
            System.out.println("Your survey is complete!");
        } catch (CancelledSurveyException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Thank you for your time.");
        }
    }

    private static String getResponseOrQuit() throws CancelledSurveyException {
        String input = scanner.next();

        if (input.equals("quit")) {
            throw new CancelledSurveyException();
        }
        
        return input;
    }

    private static int getAge() throws CancelledSurveyException {
        System.out.print("Please enter your age: ");

        String input = getResponseOrQuit();
        int age;

        try {
            age = Integer.parseInt(input);

            if (age < 0) {
                throw new IllegalArgumentException();
            }

            if (age <= 18) {
                throw new CancelledSurveyException("You are too young to complete the survey.");
            }
        } catch (IllegalArgumentException iae) {
            System.out.println("You've entered an invalid age.");
            return getAge();
        }

        return age;
    }

    private static String[][] readStateFile() {
        Scanner reader = null;
        String[][] states = new String[50][2];

        try (FileInputStream file = new FileInputStream("states.bin")) {
            DataInputStream inputStream = new DataInputStream(file);

            for (int i  = 0; inputStream.available() > 0; i++) {
                String abbreviation = inputStream.readUTF();
                String state = inputStream.readUTF();

                states[i][0] = abbreviation;
                states[i][1] = state;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return states;
    }

    private static String getState(String[][] states) throws CancelledSurveyException {
        System.out.print("Please enter the 2-letter state abbreviation: ");
        String input = getResponseOrQuit();

        for (int i = 0; i < 50; i++) {
            if (states[i][0].equals(input)) {
                return states[i][1];
            }
        }

        System.out.println("The state abbreviation was not found.");

        return getState(states);
    }

    private static int getZIPCode() throws CancelledSurveyException {
        System.out.print("Please enter your zip code: ");
        String input = getResponseOrQuit();

        try {
            int zipCode = Integer.parseInt(input);

            if (zipCode < 9999 || zipCode > 100000) {
                System.out.println("Invalid ZIP code.");
                return getZIPCode();
            }

            return zipCode;
        } catch (NumberFormatException nfe) {
            System.out.println("Invalid input.");
        }

        return getZIPCode();
    }
}