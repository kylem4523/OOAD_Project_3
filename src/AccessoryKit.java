import java.util.ArrayList;

// Concrete decorator class for wrapping an existing tool with an accessory kit option.
public class AccessoryKit extends ToolDecorator {
    Tools tool;
    int price = 4;      // Price is $4
    String description = "Accessory Kit";

    public AccessoryKit(Tools tool) {
        this.tool = tool;
        this.toolName = tool.toolName;
    }

    // Concrete decorator implementation that returns the tools actual rental price plus the accessory add on.
    public int calculatePrice(int numDays) {
        return tool.calculatePrice(numDays) + this.price;
    }

    // Decorator method that adds to the list of descriptions for this tool instance.
    public ArrayList<String> getDescriptions(){
        ArrayList<String> newDescriptions = tool.getDescriptions();
        newDescriptions.add(description);
        return newDescriptions;
    }
}
