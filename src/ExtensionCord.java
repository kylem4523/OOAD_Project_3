import java.util.ArrayList;

// Concrete decorator class that is used to wrap an existing tool with an extension cord option.
public class ExtensionCord extends ToolDecorator {
    Tools tool;
    int price = 3;
    String description = "Extension Cord";

    public ExtensionCord(Tools tool) {
        this.tool = tool;
        this.toolName = tool.toolName;

    }
    //price do not vary by days
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
