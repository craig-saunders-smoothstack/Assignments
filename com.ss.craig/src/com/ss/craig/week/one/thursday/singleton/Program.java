/**
 * Concurrency : Implement a Singleton and double check locking while doing so
 */
package com.ss.craig.week.one.thursday.singleton;

import java.util.concurrent.CyclicBarrier;

/**
 * @author Craig Saunders
 *
 */
public class Program {

    private final static int threads = 10;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // Create a CyclicBarrier gate to block complete execution all the way
        // up to the getInstance() function of the singleton to test concurrency
        final CyclicBarrier gate = new CyclicBarrier(threads + 1);
        for (int i = threads; i >= 1; i--)
        {
            new Thread() {
                public void run()
                {
                    try
                    {
                        gate.await();
                        TheSingletonDCL.getInstance();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        try
        {
            gate.await();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
