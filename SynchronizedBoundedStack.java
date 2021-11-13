import java.util.concurrent.locks.*;

/**
 * Stack that uses locks to synchronize access by threads to push and pop items
 * onto the stack.
 * 
 * @author Kevin Wood
 * @version 1.0
 */
public class SynchronizedBoundedStack extends BoundedStack {

   private ReentrantLock sLock;
   private Condition fullCondition;
   private Condition emptyCondition;

   public SynchronizedBoundedStack(int capacity) {
      super(capacity);
      sLock = new ReentrantLock();
      fullCondition = sLock.newCondition();
      emptyCondition = sLock.newCondition();
   }

   /**
    * Pushes the provided element onto the top of the stack.
    * 
    * @param t : The item to push.
    * @throws InterruptedException
    */
   @Override
   public void push(int t) throws InterruptedException {
      try {
         sLock.lock();
         while (isFull()) {
            System.out.println("Stack Full");
            fullCondition.await();
         }

         elements[top] = t;
         top++;
         System.out.println("Pushing " + t + " onto the stack");
         emptyCondition.signalAll();
      } finally {
         sLock.unlock();
      }
   }

   /**
    * Returns the value on top of the stack.
    * 
    * @return : The value on top of the stack.
    */
   @Override
   public int peek() throws InterruptedException {
      try {
         while (isEmpty()) {
            System.out.println("Stack Empty");
            emptyCondition.await();
         }
         sLock.lock();
         int result = elements[top - 1];
         fullCondition.signalAll();
         return result;
      } finally {
         sLock.unlock();
      }
   }

   /**
    * Removes the top value from the stack and returns it.
    * 
    * @return : The item popped from the stack.
    * @throws InterruptedException
    */
   @Override
   public int pop() throws InterruptedException {
      try {
         sLock.lock();
         while (isEmpty()) {
            System.out.println("Stack Empty");
            emptyCondition.await();
         }

         top--;
         int result = elements[top];
         System.out.println("Popping " + result + " from the stack");
         fullCondition.signalAll();
         return result;
      } finally {
         sLock.unlock();
      }
   }

   /**
    * Returns true if the stack is empty and false otherwise.
    * 
    * @return : Whether the stack is empty.
    */
   @Override
   public boolean isEmpty() {
      return top == 0;
   }

   /**
    * Returns true if the stack is full, and false otherwise.
    * 
    * @return : Whether or not the stack is full.
    */
   @Override
   public boolean isFull() {
      return top == capacity;
   }

   /**
    * Returns the number of elements currently on the stack.
    * 
    * @return : The size of the stack.
    */
   @Override
   public int getSize() {
      return top;
   }
}
