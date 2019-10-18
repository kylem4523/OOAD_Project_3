public abstract class Customer {
    protected String name;
    String type;
    RentalBehavior rentalBehavior;  // ConcreteTool customer classes will have unique rental behavior based on what sub class they are.  This will be handled by the Strategy design pattern.
    protected int numTools;         // Field that holds the number of tools a customer currently has.
    protected int minToolRental;    // Field that holds the minimum number of tools that a given customer will rent.



    public Customer(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRentalBehavior(RentalBehavior rb){
        this.rentalBehavior = rb;
    }

    public int getNumTools() {
        return numTools;
    }

    public void setNumTools(int numTools) {
        this.numTools = numTools;
    }

    public int getMinToolRental(){
        return minToolRental;
    }

    // Strategy implementation that delegates to the rental behavior class to ensure the appropriate rental behavior is returned.
    // This returns an array of integers that includes the number of tools the customer wishes to rent, the number of days they
    // will rent for, and the number of accessories to add to the given tool respectively.
    public int[] doRental(){
        return rentalBehavior.rent();
    }

    public String getType(){
        return this.type;
    }

    // Method that checks if a customer has already achieved the maximum amount of total rentals.
    public boolean atMax(){
        return numTools == 3;
    }

    // Method that increments the number of tools that a customer has currently.
    public void incrementTools(){
        numTools++;
    }

    // Method that decreases the number of tools that a customer has currently.
    public void decreaseTools(){
        numTools--;
    }




}
