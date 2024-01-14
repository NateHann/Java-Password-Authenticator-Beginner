import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PasswordAuthenticator {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Prompt for user's email
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Password Creation
        String password = createPassword();
        // Write the password to file
        writeToFile(email, password);
        System.out.println("Password created successfully.");
    }

    // Create Password-Recursive method
    private static String createPassword() {
        System.out.print("Create a password: ");
        String password = scanner.nextLine();

        // Check if the password meets criteria
        if (!isValidPassword(password)) {
            printPasswordRequirements(password);
            // Call the method recursively until a valid password is entered
            return createPassword();
        }

        // Confirm the password
        return confirmPassword(password);
    }

    // Recursive method to confirm password
    private static String confirmPassword(String password) {
        System.out.print("Confirm your password: ");
        String confirmPassword = scanner.nextLine();

        // Check if the confirmed password matches the original
        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match. Try again.");
            // Call the createPassword method again if passwords do not match
            return createPassword();
        }

        return password;
    }

    // Method to check if the password is valid
    private static boolean isValidPassword(String password) {
        boolean hasTwoNumbers = password.matches(".*\\d.*\\d.*");
        boolean hasCapitalLetter = password.matches(".*[A-Z].*");
        boolean hasSymbol = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*");
        return hasTwoNumbers && hasCapitalLetter && hasSymbol;
    }

    // Method to print out what is missing in the password
    private static void printPasswordRequirements(String password) {
        if (!password.matches(".*\\d.*\\d.*")) {
            System.out.println("Password must contain at least two numbers.");
        }
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
        }
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*")) {
            System.out.println("Password must contain at least one symbol.");
        }
    }

    // Method to write the password to a file
    private static void writeToFile(String email, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("passwords.txt", true))) {
            writer.write("Email: " + email + ", Password: " + password);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

//I used to additional research to improve my code.
//https://stackoverflow.com/questions/64273303/using-regex-to-validate-password
//https://stackoverflow.com/questions/15393882/regex-to-match-at-least-2-digits-2-letters-in-any-order-in-a-string
//https://stackoverflow.com/questions/4736/learning-regular-expressions
//https://stackoverflow.com/questions/19605150/regex-for-password-must-contain-at-least-eight-characters-at-least-one-number-a
//I decided to add more rules/checks for the password as I thought id atleast include a minimum standard requirement instead of just requesting two numbers as this would not generally be acceptable.
