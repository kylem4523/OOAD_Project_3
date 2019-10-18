import org.junit.*;
import org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MyUnitTest {

    @Test
    // Result will return an array of the number of tools rented and the number of days rented respectively.
    // Casual Renters should rent 1 to 2 tools and should do it for a period of 1 to 2 days.
    public void testCasualRental(){
        CasualRental cs = new CasualRental();
        int[] result = cs.rent();
        Assert.assertTrue(result[0] >= 1 && result[0] <= 2);
        Assert.assertTrue(result[1] >= 1 && result[1] <= 2);
        Assert.assertTrue(result[2] >= 0 && result[2] <= 6);
    }

    // Result will return an array of the number of tools rented and the number of days rented respectively.
    // Business Renters should rent 3 tools and should do it for a period of 7 days.
    @Test
    public void testBusinessRental(){
        BusinessRental br = new BusinessRental();
        int[] result = br.rent();
        Assert.assertEquals(3, result[0]);
        Assert.assertEquals(7, result[1]);
        Assert.assertTrue(result[2] >= 0 && result[2] <= 6);
    }

    // Result will return an array of the number of tools rented and the number of days rented respectively.
    // Regular Renters should rent 1 to 3 tools and should do it for a period of 3 to 5 days.
    @Test
    public void testRegularRental(){
        RegularRental rr = new RegularRental();
        int[] result = rr.rent();
        Assert.assertTrue(result[0] >=1 && result[0] <= 3);
        Assert.assertTrue(result[1] >= 3 && result[1] <= 5);
        Assert.assertTrue(result[2] >= 0 && result[2] <= 6);
    }

    // WoodWorking Tool Price is set at $5 per day.
    // AccessoryKit add-on is set at $4 per rental.
    // Test should return the correct total price when an accessory kit is added to the WoodWorking Tool in the Decorator fashion.
    // Decorator adds two options of $4 each.
    @Test
    public void testAccessoryKitDecorator(){
        Tools t = new WoodWorkingTool("WW 1");
        t = new AccessoryKit(t);
        t = new AccessoryKit(t);
        int total = t.calculatePrice(2);
        Assert.assertEquals(18, total);
    }
    // WoodWorking Tool Price is set at $5 per day.
    // AccessoryKit add-on is set at $3 per rental.
    // Test should return the correct total price when an accessory kit is added to the WoodWorking Tool in the Decorator fashion.
    // Decorator adds two options of $3 each.
    // $5 + $5 +$5 + $3 = $18
    @Test
    public void testExtensionCordDecorator(){
        Tools t = new WoodWorkingTool("WW 1");
        t = new ExtensionCord(t);
        int total = t.calculatePrice(3);
        Assert.assertEquals(18, total);
    }

    // This tests the functionality of the createActiveRental rental method contained within the RentalRecord class.
    // After instantiating all appropriate objects we expect the function to change the availability of the given tool
    // to unavailable.  We also expect the function to create a mapping of the tool to the given customer.
    // Finally the method should set the return date of the tool to the current day plus the days the tool is rented for
    // as well as calculating the final sale of the rental and returning that sale.
    @Test
    public void testCreateActiveRental(){
        Tools ww = new WoodWorkingTool("Woodworking Tool 1");
        ww = new AccessoryKit(ww);
        Customer Jim = new RegularCustomer("Jim");
        ArrayList<Tools> tools = new ArrayList<Tools>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        tools.add(ww);
        customers.add(Jim);
        RentalRecord rr = new RentalRecord();
        int sale = rr.createActiveRental(Jim, ww, 1, 3);
        System.out.println(sale);
        rr.printActiveRentals();
        HashMap<Tools, Customer> belongsTo = rr.getToolRentedBy();
        Assert.assertTrue(belongsTo.get(ww) == Jim);
        Assert.assertTrue(rr.activeRentals.size() == 1);
        Assert.assertTrue(ww.returnDate == 4);
        Assert.assertEquals(19, sale);
    }

    // This test uses the same set up as the CreateActiveRental test.  After creating an active rental
    // we expect the removal of the active rental to set the availability of the tool to true, and
    // remove the record from the active rentals and remove the tool from the customer.
    @Test
    public void testRemoveActiveRental(){
        Tools ww = new WoodWorkingTool("Woodworking Tool 1");
        Customer Jim = new RegularCustomer("Jim");
        ArrayList<Tools> tools = new ArrayList<Tools>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        tools.add(ww);
        customers.add(Jim);
        RentalRecord rr = new RentalRecord();
        rr.createActiveRental(Jim, ww, 1, 3);
        rr.removeActiveRental(ww);
        Assert.assertTrue(ww.isAvailability);
        Assert.assertTrue(rr.activeRentals.size() == 0);
        Assert.assertTrue(rr.toolRentedBy.size() == 0);
    }

    // Tests the days rented method.
    @Test
    public void testDaysRented(){
        Tools ww = new WoodWorkingTool("Woodworking Tool 1");
        ww.setReturnDate(1, 10);
        ww.setDayRented(1);
        Assert.assertEquals(10, ww.getReturnDate() - ww.getDayRented());
    }

    // Tests the create completed record method.
    @Test
    public void testCreateCompletedRecord(){
        Tools ww = new WoodWorkingTool("Woodworking Tool 1");
        ww = new AccessoryKit(ww);
        Customer Jim = new RegularCustomer("Jim");
        ArrayList<Tools> tools = new ArrayList<Tools>();
        ArrayList<Customer> customers = new ArrayList<Customer>();
        tools.add(ww);
        customers.add(Jim);
        RentalRecord rr = new RentalRecord();
        rr.createActiveRental(Jim, ww, 1, 3);
        rr.createCompletedRecord(ww);
        System.out.println(rr.completedRecord.get(0));
        Assert.assertTrue(rr.activeRentals.size() == 0);
        Assert.assertTrue(rr.toolRentedBy.size() == 0);
        Assert.assertTrue(rr.completedRecord.size() == 1);
    }

    // Test the get description which should return the tool type
    // as well as any add ons that it was wrapped with.
    @Test
    public void testGetDescription(){
        Tools t = new WoodWorkingTool("WW 1");
        t = new AccessoryKit(t);
        t = new AccessoryKit(t);
        ArrayList<String> descriptions = t.getDescriptions();
        Assert.assertTrue(descriptions.size() == 3);
    }

    // Tests the removeOptions method which should return
    // a list of descriptions size 1 when reset.
    @Test
    public void testRemoveOptions(){
        Tools t = new WoodWorkingTool("WW 1");
        t = new AccessoryKit(t);
        t = new AccessoryKit(t);
        t.resetDescriptions();
        Assert.assertTrue(t.descriptions.size() == 1);
    }

    // Test the getAvailableTools method when the store is initialized
    // which should return 24 tools.
    @Test
    public void testGetAvailableTools() {
        ArrayList<Tools> tools = new ArrayList<Tools>();
        Hardware_Store hs = Hardware_Store.getInstance();
        tools = hs.getAvailableTools();
        Assert.assertEquals(24, tools.size());
    }
}
