
public class BeverageItem extends MenuItem {
    private String size;

  
    public BeverageItem(String name, double price, String size) {
       
        super(name, price, "Beverage");
        this.size = size;
    }

    public String getSize() {
        return size;
    }


    
    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", getName(), size, getPrice());
    }
}