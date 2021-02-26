/**
 * 
 */
package com.ss.craig.week.one.thursday.deadlock;

/**
 * @author craig
 *
 */
public class ShinyObject {
    public String shiny = null;

    public ShinyObject(String obj)
    {
        this.shiny = obj;
    }
    
    public void takeBackWhatIsMine(String shiny)
    {
        this.shiny = shiny;
    }
    
    public int getHashCode()
    {
        return shiny.hashCode();
    }
}
