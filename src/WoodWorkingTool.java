import java.util.ArrayList;

public class WoodWorkingTool extends Tools {

    // Constructor for woodworking tool with a price of $5.
    public WoodWorkingTool(String toolName) {
        this.categoryName = "Wood Working Tool";
        this.price = 5;
        this.isAvailability = true;
        this.toolName = toolName;
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
