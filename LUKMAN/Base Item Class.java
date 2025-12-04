/**
 * File: MenuItem.java
 *
 * Base class representing any item available for sale in the POS system.
 * Demonstrates: Encapsulation (private fields with public getters)
 */
public class MenuItem {
    private String name;
    private double price;
    private String category;

    /**
     * Constructor for MenuItem.
     * @param name The name of the item.
     * @param price The price of the item.
     * @param category The category (e.g., Food, Beverage, Side).
     */
    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // --- Getters (Encapsulation) ---

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    /**
     * Polymorphism: Standard string representation of the item.
     * Derived classes will override this for specialized output.
     */
    @Override
    public String toString() {
        return String.format("%s (%s): $%.2f", name, category, price);
    }
}