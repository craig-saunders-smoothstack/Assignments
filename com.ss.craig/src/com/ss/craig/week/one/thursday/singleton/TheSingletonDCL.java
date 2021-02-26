/**
 * Concurrency : Implement a Singleton and double check locking while doing so
 */
package com.ss.craig.week.one.thursday.singleton;

/**
 * @author Craig Saunders
 *
 */
public class TheSingletonDCL {

    private volatile static TheSingletonDCL instance = null;

    /**
     * private constructor so that no funny business happens
     */
    private TheSingletonDCL()
    {
        System.out.println("TheSingleton says: I EXIST! But only once.");
    }

    /**
     * Singleton instance getter. Should only create a new instance once without any
     * concurrency issues
     * 
     * @return : returns the one and only instance this program will ever need
     */
    synchronized public static TheSingletonDCL getInstance()
    {
        // Double check locking
        if (instance == null)
        {
            // Locks the constructor of this class and not the volatile instance variable
            synchronized (TheSingletonDCL.class)
            {
                if (instance == null)
                {
                    // since the constructor is locked to one thread,
                    // only once will this constructor be called
                    instance = new TheSingletonDCL();
                }
            }
        }
        return instance;
    }
}
