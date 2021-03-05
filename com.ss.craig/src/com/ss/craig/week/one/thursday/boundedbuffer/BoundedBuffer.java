/**
 * Thursday Assignment: Create a producer thread and consumer thread sharing a bounded buffer
 */
package com.ss.craig.week.one.thursday.boundedbuffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Craig Saunders
 *
 */
public class BoundedBuffer {
    private final static int min_sleep_seconds = 1;
    private final static int max_sleep_seconds = 8;
    private static boolean resume = true;
    private static int max_buffer_size = 2;
    private static boolean p_running = true;
    private static boolean c_running = true;
    private static boolean t_running = true;

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        List<Integer> boundedBuffer = new ArrayList<>();

        // Producer producing random ints at a max of 4 total, but should only be
        // capable of 2
        Runnable producer = new Runnable() {
            @Override
            public void run()
            {
                while (resume)
                {
                    if (boundedBuffer.size() <= max_buffer_size)
                    {
                        boundedBuffer.add(getRandomInt(1, 9999));
                    }
                    try
                    {
                        Thread.sleep(getRandomInt(min_sleep_seconds * 1000, max_sleep_seconds * 1000));
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
                p_running = false;
                System.out.println("Producer thread stopped.");
            }
        };

        // Consumer consuming all in the buffer every possible max buffer
        Runnable consumer = new Runnable() {
            @Override
            public void run()
            {
                while (resume)
                {
                    if (boundedBuffer.size() > 0)
                    {
                        System.out.println(boundedBuffer.remove(boundedBuffer.size() - 1));
                    }
                    else
                    {
                        try
                        {
                            Thread.sleep(min_sleep_seconds * max_buffer_size * 1000);
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                c_running = false;
                System.out.println("Consumer thread stopped.");
            }
        };

        // Timer thread to keep track of time while outputting the integers
        Runnable second_timer_display = new Runnable() {
            @Override
            public void run()
            {
                while (resume)
                {
                    try
                    {
                        Thread.sleep(1000);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    System.out.print(".");
                }
                t_running = false;
                System.out.println("Timer display thread stopped.");
            }
        };

        System.out.println("Starting threads...");
        new Thread(producer).start();
        new Thread(consumer).start();
        new Thread(second_timer_display).start();

        System.out.println("Enter any key to stop the program:");
        Scanner scnr = new Scanner(System.in);
        if (scnr.hasNext())
        {
            resume = false;
        }

        try
        {
            scnr.close();
            System.out.println("Waiting for Threads to stop...");
            while (p_running || c_running || t_running)
            {
                Thread.sleep(500);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Threads stopped: Exiting...");
    }

    /**
     * Get a random integer
     * 
     * @param min
     * @param max
     * @return Returns a random integer from min to max
     */
    private static int getRandomInt(int min, int max)
    {
        return (int) (Math.random() * (max - min) + min);
    }

}
