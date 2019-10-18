public class CasualCustomer extends Customer {

    public CasualCustomer(String name){
        this.name = name;
        this.minToolRental = 1;
        this.rentalBehavior = new CasualRental();   // Makes use of the Strategy pattern to set the appropriate rental behavior for this class.
        this.type = "Casual";
    }

}
