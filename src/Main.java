import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Add 12 Customers in list
        ArrayList<Customer> customers = new ArrayList<Customer>();

        //Business customers
        Customer a = new BusinessCustomer("a");
        customers.add(a);
        Customer b = new BusinessCustomer("b");
        customers.add(b);
        Customer c = new BusinessCustomer("c");
        customers.add(c);
        Customer d = new BusinessCustomer("d");
        customers.add(d);


        Customer e = new CasualCustomer("e");
        customers.add(e);
        Customer f = new CasualCustomer("f");
        customers.add(f);
        Customer g = new CasualCustomer("g");
        customers.add(g);
        Customer h = new CasualCustomer("h");
        customers.add(h);


        Customer iCust = new RegularCustomer("iCust");
        customers.add(iCust);
        Customer j = new RegularCustomer("j");
        customers.add(j);
        Customer k = new RegularCustomer("k");
        customers.add(k);
        Customer l = new RegularCustomer("l");
        customers.add(l);
        // Instantiating the hardware store with singleton and the rental record that
        // the store will use.
        Hardware_Store hs = Hardware_Store.getInstance();
        RentalRecord rr = new RentalRecord();
        Random random = new Random();
        // Starting the simulation.
        while (hs.getCurrentDay() < 36) {
            hs.displayDayNumber();

            // Checks to see if any returns need to be made.
            if (hs.rentedTools.size() > 0) {
                ArrayList<Tools> returns = hs.getRentalReturns();
                for (int n = 0; n < returns.size(); n++) {
                    Tools returnTool = returns.get(n);
                    hs.returnTool(returnTool);
                    rr.createCompletedRecord(returnTool);
                }
            }
            rr.displayCompletedRecords();
            rr.printActiveRentals();
            System.out.println("Number of available tools: " + hs.getNumAvailableTools());
            hs.displayAvailableTools();
            ArrayList<Customer> valids = hs.generateValidCustomers(customers);
            ArrayList<Customer> todaysCustomers = hs.todaysCustomers(valids);
            // Loops through the stores customers for the day.
            for (int i = 0; i < todaysCustomers.size(); i++) {
                // TODO Possible bug with available tools
                Customer currentCust = todaysCustomers.get(i);
                todaysCustomers.remove(currentCust);
                ArrayList<Tools> availableTools = hs.getAvailableTools();
                int numAvailableTools = hs.getNumAvailableTools();
                int[] currentRentalBehavior = currentCust.doRental();   // Returns the rental strategy for the given customer.
                int numToolsToRent = currentRentalBehavior[0];          // Number of tools the customer will rent.
                int numDays = currentRentalBehavior[1];                 // Number of days that the customer will rent the tool for.
                while (numAvailableTools > 0 && numToolsToRent > 0) {   // While the customer still wants to get more tools and there are still tools to rent.
                    int numAccessories = random.nextInt(6);      // Adding accessories
                    Tools currentRental = hs.rentTool();
                    if (currentCust.type.compareTo("Regular") == 0) {
                        hs.incrementCasualCust();
                    } else if (currentCust.type.compareTo("Business") == 0) {
                        hs.incrementBusinessCust();
                    } else {
                        hs.incrementRegularCust();
                    }
                    while (numAccessories > 0) {
                        if (currentRental != null) {
                            currentRental = hs.wrapRandomAccessory(currentRental);
                            numAccessories--;
                        }
                    }
                    int sale = rr.createActiveRental(currentCust, currentRental, hs.getCurrentDay(), numDays);  // Total sale for given rental.
                    hs.incrementTotalRentals();
                    numAvailableTools--;
                    numToolsToRent--;
                    //availableTools.remove(0);
                    hs.incrementDailyTotal(sale);
                }
            }

            hs.incrementTotalMoney(hs.getDailyTotal());
            hs.resetDailyTotal();
            hs.newDay();

        }

        System.out.println("Number of Total Rentals: " + hs.getTotalRentals());
        System.out.println("Number of Total Rentals by Casual Customers: " + hs.getNumCasualCustomers());
        System.out.println("Number of Total Rentals Business Customers: " + hs.getNumBusinessCustomers());
        System.out.println("Number of Total Rentals By Regular Customers: " + hs.getNumRegularCustomers());
        System.out.println("Total Money Made: " + hs.getTotalMoney());
    }
}
