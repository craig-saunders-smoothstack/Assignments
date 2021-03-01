/**
 * 
 */
package com.ss.craig.week.one.weekend.assignment.five;

/**
 * @author Craig Saunders
 *
 */
public class GroupSumClump {
    
    public boolean groupSumClump(Integer[] int_array, Integer target)
    {
        if (int_array.length > 0)
        {            
            int total = int_array[0];
            int count = 0;
            while(count+1 < int_array.length && int_array[count] == int_array[count+1])
            {
                total += int_array[count+1];
                count++;                
            }
            
            Integer[] new_array = new Integer[int_array.length-(count+1)];
            
            for (int i = count+1; i < int_array.length; i++)
            {
                new_array[i-(count+1)] = int_array[i];
            }
            int_array = new_array;
            new_array = null;
            if (groupSumClump(int_array, target-total) || 
                    groupSumClump(int_array, target))
            {
                return true;
            }
            return false;
        }
        else
        {
            return target == 0;
        }
    }
}
