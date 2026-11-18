public class FeeService {
    public String pay(String registrationNumber, String amountText) {
        if (registrationNumber == null || registrationNumber.trim().isEmpty())
            throw new IllegalArgumentException("Registration number is required.");
        final double amount;
        try { amount = Double.parseDouble(amountText.trim()); }
        catch (Exception ex) { throw new IllegalArgumentException("Enter a valid amount."); }
        if (!Double.isFinite(amount) || amount <= 0)
            throw new IllegalArgumentException("Amount must be greater than zero.");
        // Demonstration only: connect a FeeRepository here for real payment persistence.
        return String.format("Payment accepted: %.2f", amount);
    }
}
