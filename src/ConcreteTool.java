import java.util.ArrayList;

public class ConcreteTool extends Tools {

    // Constructor for concrete tool with a price of $15.
    public ConcreteTool(String name) {
        this.categoryName = "ConcreteTool";
        this.price = 15;
        this.isAvailability = true;
        this.toolName = name;
        this.descriptions.add(toolName);
    }

    //Using day to set price
    private void setPrice(int price) {
        this.price = price;
    }

    private float getPrice() {
        return this.price;
    }

}
