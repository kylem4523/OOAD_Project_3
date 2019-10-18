import java.util.Random;

public class BusinessRental implements RentalBehavior {
    // Concrete Business customer strategy method that causes a customer to rent 3 tools for 7 days as well
    // as the the number of add ons and returns an array with that information.
    public int[] rent(){
        Random random = new Random();
        int numTools = 3;
        int numNights = 7;
        int numOptions = random.nextInt(7);
        int[] rentalInfo = new int [3];
        rentalInfo[0] = numTools;
        rentalInfo[1] = numNights;
        rentalInfo[2] = numOptions;
        return rentalInfo;
    }
}
