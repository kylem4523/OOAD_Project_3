import java.util.Random;

public class CasualRental implements RentalBehavior {
    // Casual Customer strategy method that causes a customer to rent 1 to 2 tools for 1 to 2 days
    // as well as the number of additional options and returns an array with that information.
    public int[] rent(){
        Random random = new Random();
        int numTools = random.nextInt(2) + 1;
        int numNights = random.nextInt(2) + 1;
        int numOptions = random.nextInt(7);
        int[] rentalInfo = new int [3];    // Array that holds the number of tools rented and number of nights rented respectively.
        rentalInfo[0] = numTools;
        rentalInfo[1] = numNights;
        rentalInfo[2] = numOptions;
        return rentalInfo;
    }
}
