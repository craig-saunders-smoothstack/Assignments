/**
 * Thursday Assignment: create a program that deadlocks two threads with each other
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
        ShinyObject shiny_one = new ShinyObject("Two's Shiny");
        ShinyObject shiny_two = new ShinyObject("One's Shiny");

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

        new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    synchronized (shiny_two)
                    {
                        Thread.sleep(100);
                        synchronized (shiny_one)
                        {
                            shiny_one.takeBackWhatIsMine(shiny_two.shiny);
                        }
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }).start();

        System.out.println("And the two threads lived happily ever after");
    }

}
