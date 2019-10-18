import java.util.ArrayList;

public class YardWorkingTool extends Tools {

    // Constructor for Yard Working tool with a price of $5.
    public YardWorkingTool(String name) {
        this.categoryName = "Yard Working Tool";
        this.price = 5;
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
