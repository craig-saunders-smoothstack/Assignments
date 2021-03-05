/**
 * Thursday Assignment: create a program that deadlocks two threads with each other
 * 
 * The idea of using two threads fighting over shiny objects came from an image
 * in Pramod's training slides where one thread wanted the other thread's object
 * and vice versa.
 */
package com.ss.craig.week.one.thursday.deadlock;

/**
 * @author Craig Saunders
 *
 */
public class DeadlockedThreads {

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        // The two shiny objects
        ShinyObject shiny_one = new ShinyObject("Two's Shiny");
        ShinyObject shiny_two = new ShinyObject("One's Shiny");

        // The first thread which tries to lock shiny_one and shiny_two
        startNewThread(shiny_one, shiny_two);

        /*
         * The second thread which tries to lock the same threads as the first one, but
         * in the opposite order, deadlocking the two threads waiting for each other to
         * "give up their shiny first".
         */
        startNewThread(shiny_two, shiny_one);

        System.out.println("And the two threads lived happily ever after");
    }

    private static void startNewThread(ShinyObject shiny_one, ShinyObject shiny_two)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    synchronized (shiny_one)
                    {
                        Thread.sleep(100);
                        synchronized (shiny_two)
                        {   
                            // this never happens because of deadlock
                            shiny_two.takeBackWhatIsMine(shiny_one.shiny);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
