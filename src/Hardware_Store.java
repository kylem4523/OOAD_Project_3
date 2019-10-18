import javax.tools.Tool;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;


public class Hardware_Store {

    private static Hardware_Store HdStore;

    private static int totalMoney = 0;              // Keeps track of the total money that the store has made.
    private static int totalRentals = 0;            // Keeps track of the total number of rentals the store has made.
    private static int numBusinessCustomers = 0;    // Keeps track of the total number of business customers.
    private static int numCasualCustomers = 0;      // Keeps track of the total number of Casual Customers.
    private static int numRegularCustomers = 0;     // Keeps track of the total number of Regular Customers.
    private static int currentDay = 1;              // Current day counter
    ArrayList<Tools> availableTools;                // List of current available tools.
    ArrayList<Tools> rentedTools;                   // List of current tools out for rental.
    private int dailyTotal;                         // Keeps track of all the money the store has made for a certain day.


    // Constructor for hardware store that instantiates all the tools the store will have
    // and places them into the available tools list initially.
    private Hardware_Store() {
        //Add 24 tools into arraylist
        ArrayList<Tools> toolList = new ArrayList<Tools>();
        this.availableTools = new ArrayList<Tools>();
        this.rentedTools = new ArrayList<Tools>();

        //Painting tools
        Tools paint1 = new Paint("paint1");
        toolList.add(paint1);
        Tools paint2 = new Paint("paint2");
        toolList.add(paint2);
        Tools paint3 = new Paint("paint4");
        toolList.add(paint3);
        Tools paint4 = new Paint("paint4");
        toolList.add(paint3);

        //Concrete tools
        Tools concrete1 = new ConcreteTool("concrete1");
        toolList.add(concrete1);
        Tools concrete2 = new ConcreteTool("concrete2");
        toolList.add(concrete2);
        Tools concrete3 = new ConcreteTool("concrete3");
        toolList.add(concrete3);
        Tools concrete4 = new ConcreteTool("concrete4");
        toolList.add(concrete4);
        Tools concrete5 = new ConcreteTool("concrete5");
        toolList.add(concrete5);

        //Plumbing tools
        Tools plumbing1 = new Plumbing("plumbing1");
        toolList.add(plumbing1);
        Tools plumbing2 = new Plumbing("plumbing2");
        toolList.add(plumbing2);
        Tools plumbing3 = new Plumbing("plumbing3");
        toolList.add(plumbing3);
        Tools plumbing4 = new Plumbing("plumbing4");
        toolList.add(plumbing4);
        Tools plumbing5 = new Plumbing("plumbing5");
        toolList.add(plumbing5);

        //Woodwork
        Tools woodwork1 = new WoodWorkingTool("woodwork1");
        toolList.add(woodwork1);
        Tools woodwork2 = new WoodWorkingTool("woodwork2");
        toolList.add(woodwork2);
        Tools woodwork3 = new WoodWorkingTool("woodwork3");
        toolList.add(woodwork3);
        Tools woodwork4 = new WoodWorkingTool("woodwork4");
        toolList.add(woodwork4);
        Tools woodwork5 = new WoodWorkingTool("woodwork5");
        toolList.add(woodwork5);

        //yardwork
        Tools yardwork1 = new YardWorkingTool("yardwork1");
        toolList.add(yardwork1);
        Tools yardwork2 = new YardWorkingTool("yardwork2");
        toolList.add(yardwork2);
        Tools yardwork3 = new YardWorkingTool("yardwork3");
        toolList.add(yardwork3);
        Tools yardwork4 = new YardWorkingTool("yardwork4");
        toolList.add(yardwork4);
        Tools yardwork5 = new YardWorkingTool("yardwork5");
        toolList.add(yardwork5);

        availableTools = toolList;      // Initializing available tools to all tools


    }

    //Singleton pattern
    public static Hardware_Store getInstance() {
        if (HdStore == null)
            HdStore = new Hardware_Store();
        return HdStore;
    }

    // Getter method for daily total field.
    public int getDailyTotal() {
        return dailyTotal;
    }

    // Method that increases the daily total when a sale is made.
    public void incrementDailyTotal(int sale) {
        this.dailyTotal = dailyTotal + sale;
    }

    // Getter method for total money.
    public static int getTotalMoney() {
        return totalMoney;
    }

    // Method that is called at the end of the day to reset daily total to 0.
    public void resetDailyTotal(){
        this.dailyTotal = 0;
    }

    // Increments the current day field.
    public void newDay(){
        currentDay++;
    }

    // Getter for the size of the available tools list.
    public int getNumAvailableTools(){
        return availableTools.size();
    }

    // Return a list of all the currently available tools.
    public ArrayList<Tools> getAvailableTools(){
        return availableTools;
    }

    // This method returns a tool to be rented that will be passed into active rentals and
    // changes the tool from the available list to rented list.
    public Tools rentTool(){
        try{
            Tools tool = availableTools.get(0);
            availableTools.remove(tool);
            rentedTools.add(tool);
            return tool;
        }
        catch(IndexOutOfBoundsException e){
            System.out.println("No available tools to rent");
        }
        return null;
    }

    // This method returns the given tool to the store and removes it from the rented tools list.
    public void returnTool(Tools tool){
        availableTools.add(tool);
        rentedTools.remove(tool);
    }

    // This method checks if a tool is supposed to be returned for the current day
    // and returns a list of all tools that are supposed to be back on the given day.
    public ArrayList<Tools> getRentalReturns(){
        ArrayList<Tools> returnsList = new ArrayList<Tools>();
        for(int i = 0; i < rentedTools.size(); i++){
            Tools current = rentedTools.get(i);
            if(current.returnDate == currentDay){
                returnsList.add(current);
            }
        }
        return returnsList;
    }


    public void displayAvailableTools(){
        System.out.println("Available Tools");
        for(int i = 0; i < availableTools.size(); i++){
            Tools current = availableTools.get(i);
            System.out.println(current.toolName);
        }
    }

    public void displayDayNumber(){
        System.out.println("Day Number" + currentDay);
    }

    // Getter for current day field.
    public int getCurrentDay(){
        return this.currentDay;
    }


    // Increments the number of regular customers who have rented.
    public void incrementRegularCust(){
        numRegularCustomers++;
    }

    // Increments the number of Business customers who have rented.
    public void incrementBusinessCust(){
        numBusinessCustomers++;
    }

    // Increments the number of Casual customers who have rented.
    public void incrementCasualCust(){
        numCasualCustomers++;
    }

    // Method that acts as a decorator by adding accessories to the given tool.
    public Tools wrapRandomAccessory(Tools t){
        Random random = new Random();
        int selection = random.nextInt(3);
        if(selection == 0){
            t = new AccessoryKit(t);
        }
        else if(selection == 1){
            t = new ExtensionCord(t);
        }
        else{
            t = new ProtectiveGearPackage(t);
        }

        return t;
    }

    public static int getNumBusinessCustomers() {
        return numBusinessCustomers;
    }

    public static int getNumCasualCustomers() {
        return numCasualCustomers;
    }

    public static int getNumRegularCustomers() {
        return numRegularCustomers;
    }

    public void incrementTotalRentals(){
        totalRentals++;
    }


    public int getTotalRentals(){
        return totalRentals;
    }

    // Method that increments the total money the store has made at the end of the day by tallying total daily sales.
    public static void incrementTotalMoney(int dailySale) {
        totalMoney = totalMoney + dailySale;
    }

    // Method that generates a list of customers who are able to rent for a given day.
    public ArrayList<Customer> generateValidCustomers(ArrayList<Customer> customers){
        ArrayList<Customer> valids = new ArrayList<Customer>();
        int numAvailable = availableTools.size();
        for(int i = 0; i < customers.size(); i++){
            Customer current = customers.get(i);
            if(!current.atMax() && current.minToolRental < numAvailable){
                valids.add(current);
            }
        }
        return valids;
    }

    // Method that returns a list of random valid customers who will be the stores customers that day.
    // Code for this section taken from https://www.geeksforgeeks.org/randomly-select-items-from-a-list-in-java/
    // Credits to Rajput-Ji
    public ArrayList<Customer> todaysCustomers(ArrayList<Customer> valids){
        ArrayList<Customer> todaysCustomers = new ArrayList<Customer>();
        Random random = new Random();
        for(int i = 0; i < valids.size(); i ++){
            int randomIndex = random.nextInt(valids.size());
            todaysCustomers.add(valids.get(randomIndex));
            valids.remove(randomIndex);
            return todaysCustomers;
        }
        return todaysCustomers;
    }

}
