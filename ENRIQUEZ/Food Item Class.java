/**
 * File: FoodItem.java
 *
 * Derived class from MenuItem.
 * Demonstrates: Inheritance.
 */
public class FoodItem extends MenuItem {
    private boolean isVegetarian;

    /**
     * Constructor calls the parent (super) constructor and adds a specific field.
     */
    public FoodItem(String name, double price, boolean isVegetarian) {
        // Calls the MenuItem(name, price, category) constructor
        super(name, price, "Food");
        this.isVegetarian = isVegetarian;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }

    /**
     * Polymorphism: Overriding the parent's toString method for specialization.
     */
    @Override
    public String toString() {
        String dietary = isVegetarian ? " (Veggie)" : " (Meat)";
        return String.format("%s%s - $%.2f", getName(), dietary, getPrice());
    }
}