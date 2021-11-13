/**
 * Application that uses threads to push and pop
 * random numbers onto a stack. Application works seamlessly
 * with locks and prevents deadlocking issues.
 * 
 * @author Kevin Wood
 * @version 1.0
 */
public class Driver {
    
    public static void main(String[] args) {

        //Initialization
        SynchronizedBoundedStack randomNumbers = new SynchronizedBoundedStack(10);
        Producer p1 = new Producer(randomNumbers);
        Producer p2 = new Producer(randomNumbers);
        Consumer c1 = new Consumer(randomNumbers);
        Consumer c2 = new Consumer(randomNumbers);

        //Producer Threads
        Thread pt1 = new Thread(p1);
        Thread pt2 = new Thread(p2);

        //Consumer Threads
        Thread ct1 = new Thread(c1);
        Thread ct2 = new Thread(c2);

        //Starting Threads
        pt1.start();
        pt2.start();

        ct1.start();
        ct2.start();
    }
}
