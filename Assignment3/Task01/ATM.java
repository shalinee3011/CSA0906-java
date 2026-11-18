import java.io.*;
import java.util.*;

public class ATM {
    static void withdraw(String account, String amountText, double balance, String cardFile) {
        try {
            int amount = Integer.parseInt(amountText);
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
            if (amount > balance) throw new IllegalStateException("Insufficient balance");
            try (FileInputStream ignored = new FileInputStream(cardFile)) {
                System.out.println("Account " + account + ": Dispensed " + amount);
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println("Input error: enter a whole-number amount");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid withdrawal: " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("Transaction declined: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Card reader error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
        } finally {
            System.out.println("ATM session closed");
        }
    }
    public static void main(String[] args) {
        String card = args.length > 0 ? args[0] : "card-reader.txt";
        System.out.println("Case 1"); withdraw("A100","200",1000,card);
        System.out.println("Case 2"); withdraw("A100","abc",1000,card);
        System.out.println("Case 3"); withdraw("A100","-20",1000,card);
        System.out.println("Case 4"); withdraw("A100","2000",1000,card);
        System.out.println("Case 5"); withdraw("A100","100",1000,"missing-card-reader.txt");
    }
}
