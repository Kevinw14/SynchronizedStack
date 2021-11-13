/**
 * Class that is designed to be run on a thread.
 * Each thread removes 50 random numbers from the stack.
 * 
 * @author Kevin Wood
 * @version 1.0
 */
public class Consumer implements Runnable {
    private SynchronizedBoundedStack randomNumbers;

    public Consumer(SynchronizedBoundedStack randomNumbers) {
        this.randomNumbers = randomNumbers;
    }

    /**
     * Loops through 50 times popping 
     * 50 random numbers off of the stack
     */
    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                randomNumbers.pop();
            } catch (InterruptedException e) {
                System.out.println("Ran Into An Exception");
            }
        }
    }
}
