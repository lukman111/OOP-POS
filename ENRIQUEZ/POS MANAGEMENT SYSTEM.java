import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class POSSystem {
    
    private Map<String, MenuItem> menu;
    private List<Order> orders;
    private int nextOrderId;
    private double globalDiscountRate; 

    public POSSystem() {
        this.menu = new HashMap<>();
        this.orders = new ArrayList<>();
        this.nextOrderId = 1001;
        this.globalDiscountRate = 0.0;
    }

   
    public void setupMenu(List<MenuItem> items) {
        for (MenuItem item : items) {
            this.menu.put(item.getName(), item);
        }
        System.out.println("System initialized and Menu loaded successfully.");
    }


    
    public void displayMenu() {
        System.out.println("\n--- Current Menu ---");
        

        List<String> categories = new ArrayList<>(
            menu.values().stream()
                .map(MenuItem::getCategory)
                .distinct()
                .toList()
        );
        categories.sort(Comparator.naturalOrder());

        for (String category : categories) {
            System.out.printf("\n[%s]%n", category.toUpperCase());
            menu.values().stream()
                .filter(item -> item.getCategory().equals(category))
                .forEach(item -> System.out.printf("  %-20s - $%.2f%n", item.getName(), item.getPrice()));
        }
        System.out.println("--------------------");
    }

 
    public Order startNewOrder() {
        Order newOrder = new Order(nextOrderId++);
        orders.add(newOrder);
        System.out.printf("\n--- Started New Order #%d ---%n", newOrder.getOrderId());
        return newOrder;
    }

    public boolean processPayment(Order order, String paymentType) {
        if (!order.getStatus().equals("Pending")) {
            System.out.printf("Error: Order #%d is already %s.%n", order.getOrderId(), order.getStatus());
            return false;
        }

      
        double grandTotal = order.calculateTotal(globalDiscountRate)[3];
        
        System.out.printf("\n--- Processing Payment for Order #%d ---%n", order.getOrderId());
        System.out.printf("Amount Due: $%.2f (%.1f%% Discount Applied)%n", grandTotal, globalDiscountRate);
        
       
        boolean success = false;
        switch (paymentType.toLowerCase()) {
            case "card":
                System.out.println("Simulating Card Transaction... Success!");
                success = true;
                break;
            case "cash":
                System.out.println("Simulating Cash Payment... Success!");
                success = true;
                break;
            default:
                System.out.printf("Payment type '%s' not recognized. Transaction failed.%n", paymentType);
                success = false;
                break;
        }

        if (success) {
            order.setStatus("Completed");
            System.out.printf("Order #%d Completed.%n", order.getOrderId());
            // Display the final receipt
            order.displayReceipt(globalDiscountRate);
        }
        return success;
    }
    
    public MenuItem getMenuItem(String name) {
        return menu.get(name);
    }
    
    public void setGlobalDiscount(double percent) {
        this.globalDiscountRate = Math.max(0.0, Math.min(100.0, percent));
        System.out.printf("Global discount set to %.1f%%%n", this.globalDiscountRate);
    }



     
    public static void main(String[] args) {
        
        System.out.println("POS System Project - Demonstrating OOP Principles");
        
       
        POSSystem pos = new POSSystem();
        
     
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new FoodItem("Classic Burger", 8.99, false));
        menuItems.add(new FoodItem("Garden Salad", 6.50, true));
        menuItems.add(new BeverageItem("Espresso", 3.00, "Small"));
        menuItems.add(new BeverageItem("Latte", 4.50, "Medium"));
        menuItems.add(new MenuItem("Fries", 2.50, "Side")); 
        
        pos.setupMenu(menuItems);
        pos.displayMenu();
        
       
        pos.setGlobalDiscount(10.0); 
        
       
        Order order1 = pos.startNewOrder();
        
      
        MenuItem burger = pos.getMenuItem("Classic Burger");
        MenuItem latte = pos.getMenuItem("Latte");

        if (burger != null && latte != null) {
            order1.addItem(burger, 1);
            order1.addItem(latte, 2); 
            order1.addItem(pos.getMenuItem("Fries"), 1);

           
            pos.processPayment(order1, "Card");
        }

        
        Order order2 = pos.startNewOrder();
        MenuItem salad = pos.getMenuItem("Garden Salad");
        MenuItem espresso = pos.getMenuItem("Espresso");
        
        if (salad != null && espresso != null) {
            order2.addItem(salad, 1);
            order2.addItem(espresso, 1);
            pos.processPayment(order2, "Cash");
        }
    }
}