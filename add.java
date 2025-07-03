import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TwistedAddition {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BigDecimal> validNumbers = new ArrayList<>();
        List<String> skippedReasons = new ArrayList<>();

        System.out.println("🔢 Enter numbers (type 'done' to finish):");

        while (true) {
            System.out.print("Enter number " + (validNumbers.size() + skippedReasons.size() + 1) + ": ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("done")) break;

            try {
                BigDecimal num = new BigDecimal(input);

                if (num.scale() > 0) {  // Decimal
                    if (num.compareTo(BigDecimal.ONE) > 0) {
                        validNumbers.add(num);
                    } else {
                        skippedReasons.add(input + " ⛔ Skipped: Decimal ≤ 1.0");
                    }
                } else {  // Integer
                    int intVal = num.intValue();
                    if (intVal % 2 == 0) {
                        validNumbers.add(num);  // Even
                    } else if (isPrime(intVal)) {
                        validNumbers.add(num);  // Odd prime
                    } else {
                        skippedReasons.add(input + " ⛔ Skipped: Odd but not prime");
                    }
                }

            } catch (NumberFormatException e) {
                skippedReasons.add(input + " ⛔ Skipped: Invalid number");
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        for (BigDecimal n : validNumbers) {
            total = total.add(n);
        }

        System.out.println("\n✅ Smart Addition Result: " + total);
        System.out.println("\n🔎 Skipped Entries:");
        for (String reason : skippedReasons) {
            System.out.println(" - " + reason);
        }

        scanner.close();
    }

    // Helper to check if an integer is prime
    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
