import java.text.DecimalFormat;

public class BillCalculator {

    private static final double TAX_RATE = 0.12; // 12% VAT
    private double subtotal = 0.0;
    private final DecimalFormat df = new DecimalFormat("0.00");

    public double getSubtotal() {
        return subtotal;
    }

    public void addToSubtotal(double amount) {
        this.subtotal += amount;
    }

    public void deductFromSubtotal(double amount) {
        this.subtotal -= amount;
    }

    public void reset() {
        this.subtotal = 0.0;
    }

    public String calculateTaxString() {
        double tax = subtotal * TAX_RATE;
        return df.format(tax);
    }

    public double calculateTotal() {
        double tax = subtotal * TAX_RATE;
        return subtotal + tax;
    }

    public String calculateTotalString() {
        return df.format(calculateTotal());
    }

    public String calculateSubtotalString() {
        return df.format(subtotal);
    }

    public String calculateChangeString(double cashReceived) {
        double total = calculateTotal();
        double change = cashReceived - total;
        return df.format(change);
    }
}