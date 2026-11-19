
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) {
        super(message);
    }
}

public class ATM {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FileInputStream cardReader = null;

        int balance = 10000;

        try {
            cardReader = new FileInputStream("card.txt");
            cardReader.read();

            System.out.print("Enter Account Number: ");
            int accountNumber = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Withdrawal Amount: ");
            int amount = Integer.parseInt(sc.nextLine());

            if (amount < 0) {
                throw new IllegalArgumentException(
                    "Negative amount is not allowed");
            }

            if (amount > balance) {
                throw new InsufficientBalanceException(
                    "Insufficient account balance");
            }

            balance = balance - amount;

            System.out.println("Withdrawal Successful!");
            System.out.println("Account Number: " + accountNumber);
            System.out.println("Withdrawn Amount: " + amount);
            System.out.println("Remaining Balance: " + balance);

        } catch (NumberFormatException | IOException e) {
            System.out.println("Input/File Error: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("Bank Error: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Amount: " + e.getMessage());

        } finally {
            try {
                if (cardReader != null) {
                    cardReader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing card reader");
            }

            System.out.println("ATM Transaction Completed.");
        }

        sc.close();
    }
}
