/**
 * Tuesday Assignment: Construct 2D array, find the max number in array, and show the 2d index
 */
package com.ss.craig.week.one.tuesday.maxnumber;

/**
 * @author Craig Saunders
 *
 */
public class MaxNumber {

    private final int min_elements = 2;
    private final int max_elements = 50;
    private final int min_number = 1;
    private final int max_number = 99999;
    private int[][] two_dim_array = {{}};
    private int first_index = 0;
    private int second_index = 0;
    
    /**
     * Find and display max number in 2d array from your own construction
     */
    public MaxNumber () {
        constructArray();
        showPosition(findMaxNumber());
    }
    
    /**
     * Finds the max number in the 2d array
     * @return : returns array of three integers
     *      int[0] = max number
     *      int[1] = one dimentional index
     *      int[2] = two dimentional index
     */
    private int[] findMaxNumber() {
        int[] max_num = new int[3];
        max_num[0] = 0;
        for (int i = 0; i < first_index; i++) {
            for (int j = 0; j < second_index; j++) {
                if (two_dim_array[i][j] > max_num[0]) {
                    max_num[0] = two_dim_array[i][j];
                    max_num[1] = i;
                    max_num[2] = j;
                }
            }
        }
        return max_num;
    }

    /**
     * Displays index position information of the max number in the 2d array
     * @param values : values returned by findMaxNumber
     */
    private void showPosition(int[] values) {
        if (values.length == 3) {
            System.out.print("The max number in the 2D array created is: "+Integer.toString(values[0])+
                    "\nWith a 2D index of: ["+Integer.toString(values[1])+"]["+Integer.toString(values[2])+"]");
        }
    }

    /**
     * Constructs a two dimensional array with random numbers of elements and random numbers per element
     */
    private void constructArray() {
        first_index = getRandomInt(min_elements,max_elements);
        second_index = getRandomInt(min_elements,max_elements);
        two_dim_array = new int[first_index][second_index];
        for (int i = 0; i < first_index; i++) {
            for (int j = 0; j < second_index; j++) {
                two_dim_array[i][j] = getRandomInt(min_number, max_number);
            }
        }
    }
    
    /**
     * Creates a random number with Math.random accounting for min and max values supplied
     * @param min : minimum random integer
     * @param max : maximum random integer
     * @return : returns a random int with min and max accounted for
     */
    private int getRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }
}
