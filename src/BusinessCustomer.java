public class BusinessCustomer extends Customer {

    public BusinessCustomer(String name){
        this.name = name;
        this.minToolRental = 3;
        this.rentalBehavior = new BusinessRental(); // Makes use of the Strategy pattern to set the appropriate rental behavior for this class.
        this.type = "Business";
    }
}
