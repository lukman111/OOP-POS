
public class FoodItem extends MenuItem {
    private boolean isVegetarian;


    public FoodItem(String name, double price, boolean isVegetarian) {
      
        super(name, price, "Food");
        this.isVegetarian = isVegetarian;
    }

    public boolean isVegetarian() {
        return isVegetarian;
    }


     
    @Override
    public String toString() {
        String dietary = isVegetarian ? " (Veggie)" : " (Meat)";
        return String.format("%s%s - $%.2f", getName(), dietary, getPrice());
    }
}