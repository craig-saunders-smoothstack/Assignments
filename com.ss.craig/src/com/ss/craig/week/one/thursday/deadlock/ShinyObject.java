/**
 * Thursday Assignment: create a program that deadlocks two threads with each other
 */
package com.ss.craig.week.one.thursday.deadlock;

/**
 * @author Craig Saunders
 *
 */
public class ShinyObject {
    public String shiny = null;

    /**
     * Just a class made for the purpose of deadlocking And possibly entertaining...
     * 
     * @param obj
     */
    public ShinyObject(String obj)
    {
        this.shiny = obj;
    }

    /**
     * Who gave away my shiny in the first place?
     * 
     * @param shiny : my shiny object
     */
    public void takeBackWhatIsMine(String shiny)
    {
        this.shiny = shiny;
    }
}
