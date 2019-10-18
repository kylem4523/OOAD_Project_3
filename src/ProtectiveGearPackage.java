
// Concrete Decorator class that is used to wrap an existing tool with a protective gear package.
public class ProtectiveGearPackage extends ToolDecorator {
    Tools tool;
    int price;
    String description = "Protective Gear Package";

    public ProtectiveGearPackage(Tools tool) {
        this.tool = tool;
        this.price = 10;
        this.toolName = tool.toolName;

    }
    //price do not vary by days
    public int calculatePrice(int numDays) {
        return tool.calculatePrice(numDays) + this.price;
    }
}
