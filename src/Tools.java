import java.lang.reflect.Array;
import java.util.ArrayList;

public abstract class Tools {
    String toolName;
    String categoryName;
    ArrayList<String> descriptions = new ArrayList<String>();       // Field used to hold the Tool Name, and any accesories that may have been added.
    int dayRented;
    int price;
    boolean isAvailability;
    int returnDate;     //Field that sets the return date for the tool.

    public int getReturnDate() {
        return returnDate;
    }

    public int getDayRented() {
        return dayRented;
    }

    public boolean setAvailablility;

    // Method that calculates the rental fee and is used by decorator methods as well.
    public int calculatePrice(int numDays){
        return this.price * numDays;
    }

    // Sets the return date for current day + the number of days the tool is rented for.
    public void setReturnDate(int currentDay, int daysRented){
        this.returnDate = currentDay + daysRented;
    }

    // Setter for the date that a tool was rented.
    public void setDayRented(int currentDay){
        this.dayRented = currentDay;
    }

    public void setAvailablility(boolean isAvailable) {
        this.isAvailability = isAvailable;
    }

    // Returns a list of the tools name as well as any accessories that were added on with the decorator pattern.
    public ArrayList<String> getDescriptions(){
        return descriptions;
    }

    // Method that resets all of the descriptions for a tool to just the tool name.
    public void resetDescriptions(){
        ArrayList<String> tmpDescription = new ArrayList<String>();
        String name = this.toolName;
        tmpDescription.add(name);
        descriptions = tmpDescription;
    }

}
