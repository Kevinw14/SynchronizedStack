import java.util.Random;

/**
 * Class that is designed to be run on a thread.
 * Each thread adds 50 random numbers to the stack.
 * 
 * @author Kevin Wood
 * @version 1.0
 */
public class Producer implements Runnable {
    private SynchronizedBoundedStack randomNumbers;
    private Random random;

    public Producer(SynchronizedBoundedStack randomNumbers) {
        this.randomNumbers = randomNumbers;
        random = new Random();
    }

    /**
     * Loops through 50 times pushing 
     * 50 random numbers onto the stack
     */
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                int randomNumber = random.nextInt(1000);
                randomNumbers.push(randomNumber);
            } catch (InterruptedException e) {
                System.out.println("Ran Into An Exception");
            }
        }   
    }
}
