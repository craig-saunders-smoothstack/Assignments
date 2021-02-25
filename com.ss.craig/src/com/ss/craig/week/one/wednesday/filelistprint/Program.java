/**
 * Wednesday Assignment: Get and display a recursive list of 
 * files and directories from a given directory path
 */
package com.ss.craig.week.one.wednesday.filelistprint;

/**
 * @author Craig Saunders
 *
 */
public class Program {

    /**
     * @param args : 0 index should be input for a file location
     */
    public static void main(String[] args)
    {
        FileDirectoryList.printFileFolderStructure(args);
    }

}
