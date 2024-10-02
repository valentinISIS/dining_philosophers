package diningphilosophers;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChopStick {
    private static int stickCount = 0;
    private boolean iAmFree = true;
    private final int myNumber;
    private final Lock verrou = new ReentrantLock();
    private final Condition isFree = verrou.newCondition();

    public ChopStick() {
        myNumber = ++stickCount;
    }

    synchronized public boolean take() throws InterruptedException {
        verrou.lock();
        try {
            if (!iAmFree) {
                wait(1000);
                return false;
            }
            iAmFree = false;
            System.out.println("baguette " + myNumber + " prise");
            return true;
        } finally {
            verrou.unlock();
        }
        // Pas utile de faire notifyAll ici, personne n'attend qu'elle soit occupée
    }

    synchronized public void release() {
        // assert !iAmFree;
        System.out.println("baguette " + myNumber + " relâchée");
        iAmFree = true;
        notifyAll(); // On prévient ceux qui attendent que la baguette soit libre
    }

   @Override
    public String toString() {
        return "baguette #" + myNumber;
    }
    
}
