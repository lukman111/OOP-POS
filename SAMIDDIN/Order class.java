import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Order {
    private final int orderId;
    
    private List<CartItem> items;
    private String status;
    private static final double TAX_RATE = 0.08; // 8%

    
    private static class CartItem {
        MenuItem item;
        int quantity;

        public CartItem(MenuItem item, int quantity) {
            this.item = item;
            this.quantity = quantity;
        }
    }

    public Order(int orderId) {
        this.orderId = orderId;
        this.items = new ArrayList<>();
        this.status = "Pending";
    }

    public int getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

   
  
    public void addItem(MenuItem item, int quantity) {
        if (quantity > 0) {
            items.add(new CartItem(item, quantity));
            System.out.printf("  --> Added %dx %s to Order #%d%n", quantity, item.getName(), orderId);
        } else {
            System.out.println("Quantity must be positive.");
        }
    }

  
     
    public double[] calculateTotal(double discountPercent) {
        double itemSubtotal = 0.0;
        for (CartItem ci : items) {
            itemSubtotal += ci.item.getPrice() * ci.quantity;
        }
        
      
        double discountAmount = itemSubtotal * (discountPercent / 100.0);
        double discountedSubtotal = itemSubtotal - discountAmount;
        
        
        double taxAmount = discountedSubtotal * TAX_RATE;
        
       
        double grandTotal = discountedSubtotal + taxAmount;
        
        return new double[]{itemSubtotal, discountAmount, taxAmount, grandTotal};
    }

  
    public void displayReceipt(double discountPercent) {
        double[] totals = calculateTotal(discountPercent);
        double itemSubtotal = totals[0];
        double discountAmount = totals[1];
        double taxAmount = totals[2];
        double grandTotal = totals[3];
        
        String line = "=".repeat(35);
        System.out.println("\n" + line);
        System.out.printf("      RECEIPT - ORDER #%d%n", orderId);
        System.out.printf("      Date: %s%n", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(line);

        for (CartItem ci : items) {
            double lineTotal = ci.item.getPrice() * ci.quantity;
            System.out.printf("%dx %-20s @ $%.2f = $%.2f%n", 
                              ci.quantity, ci.item.getName(), ci.item.getPrice(), lineTotal);
        }

        String dashLine = "-".repeat(35);
        System.out.println(dashLine);
        System.out.printf("Items Subtotal: %20s$%.2f%n", "", itemSubtotal);
        System.out.printf("Discount (%.1f%%): %20s-$%.2f%n", discountPercent, "", discountAmount);
        System.out.printf("Tax (%.1f%%): %20s+$%.2f%n", TAX_RATE * 100, "", taxAmount);
        System.out.println(dashLine);
        System.out.printf("GRAND TOTAL: %23s$%.2f%n", "", grandTotal);
        System.out.println(line + "\n");
    }
}