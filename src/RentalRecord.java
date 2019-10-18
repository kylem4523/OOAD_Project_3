import com.sun.xml.internal.bind.v2.TODO;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class RentalRecord {
    HashMap<Tools, Customer> toolRentedBy = new HashMap<Tools, Customer>();                     // Dictionary that maps a tool to the customer who rented it.
    HashMap<Tools, Integer> toolFee = new HashMap<Tools, Integer>();              // Dictionary that maps a tool to the fee it incurred for rental.
    ArrayList<String> completedRecord = new ArrayList<String>();                       // List that holds all of the completed rental records.
    ArrayList<String> activeRentals = new ArrayList<String>();                         // List that holds the current active rentals
    static int totalRented = 0;                                                   // Field that holds the total rentals so far.

    public RentalRecord(){}

    public HashMap<Tools, Customer> getToolRentedBy() {
        return toolRentedBy;
    }

    public ArrayList<String> getCompletedRecord() {
        return completedRecord;
    }

    public ArrayList<String> getActiveRentals() {
        return activeRentals;
    }


    // This method creates an active rental at the hardware store, by removing the tool from the shelf, calculating the total price
    // of the tool rental and mapping that tool to the customer.
    public int createActiveRental(Customer customer, Tools tool, int currentDay, int daysRented){
        String record  = "Customer Name: " + customer.name + " Rented: " + tool.toolName;
        tool.setReturnDate(currentDay, daysRented);
        tool.setDayRented(currentDay);
        tool.setAvailablility(false);
        customer.incrementTools();
        totalRented++;
        int totalPrice = tool.calculatePrice(daysRented);
        toolFee.put(tool, totalPrice);
        toolRentedBy.put(tool, customer);
        activeRentals.add(record);
        return totalPrice;

    }

    // Displays all of the active rentals.
    public void printActiveRentals(){
        System.out.println("Active Rentals: ");
        for(int i = 0; i < activeRentals.size(); i++){
            String record = activeRentals.get(i);
            System.out.println(record);
        }
    }

    // This method removes any active rentals for the given tool.
    public void removeActiveRental(Tools tool){
        Customer customer = toolRentedBy.get(tool);
        String record  = "Customer Name: " + customer.name + " Rented: " + tool.toolName;
        tool.setAvailablility(true);
        activeRentals.remove(record);
        toolRentedBy.remove(tool);
    }

    // This method will create a completed rental record and requires that there is an active rental out for the given tool
    // The record contains the Tool, Options, Customer that rented the tool, how long they rented it for, and the total price
    // of the rental.
    public void createCompletedRecord(Tools tool){
        int daysRented = tool.getReturnDate() - tool.getDayRented();
        Customer customer = toolRentedBy.get(tool);
        ArrayList<String> toolsOptions = tool.getDescriptions();
        String record = new String();
        String description = "";
       if(toolsOptions.size() > 1){
            for(int i = 0; i < toolsOptions.size(); i++){
                description = description + " " + toolsOptions.get(i);
                System.out.println(description);
            }
            record = "Tool: " + description + ", Rented By: " + customer.name + ", for " + daysRented + " days at a total price of: " + toolFee.get(tool);
        }
        else{
           description = toolsOptions.get(0);
           record = "Tool: " + description + ", Rented By: " + customer.name + ", for " + daysRented + " days at a total price of: " + toolFee.get(tool);
        }
        toolFee.remove(tool);
        completedRecord.add(record);
        tool.setAvailablility(true);
        customer.decreaseTools();
        tool.resetDescriptions();
        removeActiveRental(tool);
    }

    // This method displays all of the completed records.
    public void displayCompletedRecords(){
        System.out.print("Completed Rentals: ");
        for(int i = 0; i < completedRecord.size(); i++){
            String record = completedRecord.get(i);
            System.out.println(record);
        }
    }

}
