import java.util.Random;

public class RegularRental implements RentalBehavior {
    // ConcreteTool strategy method that causes a customer to rent 1 to 3 tools for 3 to 5 days
    // as well as the number of addiontal options and returns an array with that information.
    public int[] rent(){
        Random random = new Random();
        int numTools = random.nextInt(3) + 1;
        int numNights = random.nextInt(3) + 3;
        int numOptions = random.nextInt(7);
        int[] rentalInfo = new int [3];
        rentalInfo[0] = numTools;
        rentalInfo[1] = numNights;
        rentalInfo[2] = numOptions;
        return rentalInfo;
    }
}
