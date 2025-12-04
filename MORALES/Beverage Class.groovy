/**
 * File: BeverageItem.java
 *
 * Derived class from MenuItem.
 * Demonstrates: Inheritance and specialization.
 */
public class BeverageItem extends MenuItem {
    private String size;

    /**
     * Constructor calls the parent (super) constructor and adds a specific field.
     */
    public BeverageItem(String name, double price, String size) {
        // Calls the MenuItem(name, price, category) constructor
        super(name, price, "Beverage");
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    /**
     * Polymorphism: Overriding the parent's toString method.
     */
    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", getName(), size, getPrice());
    }
}