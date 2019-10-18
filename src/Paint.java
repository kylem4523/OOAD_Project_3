import java.util.ArrayList;

// Concrete subclass of the tool class with price of the tool set to $10.
public class Paint extends Tools {

    public Paint(String name) {
        this.categoryName = "Paint";
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
