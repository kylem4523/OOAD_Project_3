import java.util.ArrayList;

// Concrete subclass of the tool class with price set to $10.
public class Plumbing extends Tools {

    // Constructor for plumbing tool with a price of $10.
    public Plumbing(String name) {
        this.categoryName = "Plumbing tool";
        this.price = 10;
        this.isAvailability = true;
        this.toolName = name;
        this.descriptions.add(toolName);
    }

    //Using day to set price
    public void setPrice(int price) {
        this.price = price;
    }

    public float getPrice() {
        return this.price;
    }


}
