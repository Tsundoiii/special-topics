package gavinchen;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
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

    private static int getAge() throws CancelledSurveyException {
        System.out.print("Please enter your age:");

        String input = scanner.next();
        int age;

        try {
            age = Integer.parseInt(input);

            if (age < 0) {
                System.out.println("You've entered an invalid age.");
                return getAge();
            }

            if (age <= 18) {
                throw new CancelledSurveyException("You are too young to complete the survey.");
            }
        } catch (NumberFormatException nfe) {
            throw new CancelledSurveyException(input.equals("quit") ? "Your survey was cancelled." : "You've entered an invalid age.");
        }

        return age;
    }

    private static String[][] readStateFile() {
        Scanner reader;
        String[][] states = new String[2][50];

        try {
            reader = new Scanner(new FileReader("states.bin"));
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        }

        for (int i = 0; i < 50; i++) {
            states[i][0] = reader.next();
            states[i][1] = reader.next();
        }

        return states;
    }

    private static String getState(String[][] states) throws CancelledSurveyException {
        String input = scanner.next();

        if (input.equals("quit")) {
            throw new CancelledSurveyException("");
        }

        for (int i = 0; i < 50; i++)
    }
}