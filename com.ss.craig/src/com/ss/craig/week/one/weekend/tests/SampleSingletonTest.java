/**
 * 
 */
package com.ss.craig.week.one.weekend.tests;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.ss.craig.week.one.weekend.assignments.SampleSingleton;

import junit.framework.TestCase;

/**
 * @author Craig Saunders
 *
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class SampleSingletonTest extends TestCase {
    public final int threads = 10;
    public final CyclicBarrier gate = new CyclicBarrier(10 + 1);
    public static SampleSingleton ss_instance;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception
    {
        for (int i = 0; i < threads; i++)
        {
            new Thread(new Runnable() {
                @Override
                public void run()
                {
                    // System.out.println("waiting");
                    try
                    {
                        gate.await();
                    }
                    catch (InterruptedException | BrokenBarrierException e)
                    {
                        e.printStackTrace();
                    }
                    // System.out.println("running");
                    ss_instance = SampleSingleton.getInstance();
                }

            }).start();
        }

        try
        {
            // If this sleep wasn't here, it'd break because the last gate.await after it
            // would come up before the other threads finish
            Thread.sleep(100);
            gate.await();
            // System.out.println("Done Waiting");

            // If this sleep wasn't here, it'd break because of ss_instance being partially
            // initialized
            // resulting in a null being passed on
            Thread.sleep(100);
        }
        catch (InterruptedException | BrokenBarrierException e)
        {
            e.printStackTrace();
        }
    }

    @Test(expected = SQLException.class)
    public void test() throws Throwable
    {
        assertEquals(ss_instance, SampleSingleton.getInstance());
        assertEquals(SampleSingleton.getInstance(), SampleSingleton.getInstance());
        SampleSingleton.getConnection();
        SampleSingleton.databaseQuery("SELECT * FROM table;", new BigDecimal(1));
    }

}
