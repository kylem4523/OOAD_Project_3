public class RegularCustomer extends Customer {

    public RegularCustomer(String name){
        this.name = name;
        this.minToolRental = 1;
        this.rentalBehavior = new RegularRental();  // Makes use of the Strategy pattern to set the appropriate rental behavior for this class.
        this.type = "Regular";
    }
}
