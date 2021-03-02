/**
 * Given an array of ints, is it possible to choose a group of some of the ints, 
 * such that the group sums to the given target, with this additional constraint: 
 * if there are numbers in the array that are adjacent and the identical value, 
 * they must either all be chosen, or none of them chosen. 
 */
package com.ss.craig.week.one.weekend.assignment;

/**
 * @author Craig Saunders
 *
 */
public class GroupSumClump {
    public boolean groupSumClump(Integer index, Integer[] int_array, Integer target)
    {
        // checks the cursor against the length of the array here
        // to remove the need for an else clause
        if (int_array.length <= index)
        {
            return target == 0; // Returns the result boolean at the end of the recursion loop
        }
        /*
         * Populate the total with the current int_array value to reduce the need for
         * the first while loop. Also adds 1 to the index (with ++ proceeding the index
         * variable) after initializing the total
         */
        int total = int_array[index++];
        // compares the current index with the next one (index - 1 because of the
        // index++ earlier)
        while (index < int_array.length && int_array[index - 1] == int_array[index])
        {
            total += int_array[index++]; // Another index++ to add one to the index afterwords
        }
        // Continues to check the rest of the array, first using clumped sums and then
        // without
        // if either one of these return true, then return true all the way back
        return groupSumClump(index, int_array, target - total) || groupSumClump(index, int_array, target);
    }
}
