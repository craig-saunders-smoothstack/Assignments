/**
 * Wednesday Assignment: Get and display a recursive list of 
 * files and directories from a given directory path
 */
package com.ss.craig.week.one.wednesday.filelistprint;

import java.io.File;
import java.util.Scanner;

/**
 * @author Craig Saunders
 *
 */
public abstract class FileDirectoryList {

    private static String folder_directory = null;

    /**
     * Prints all files and directories recursively in the specified directory
     * 
     * @param args : args from static main
     */
    public static void printFileFolderStructure(String[] args)
    {
        folder_directory = getCurrentPath();
        if (!args.equals(null) && args.length > 0)
        {
            folder_directory = args[0];
        }
        else
        {
            System.out.println("Use current directory?");
            System.out.println("Enter 'Y' or the directory name to continue:");
            Scanner scnr = new Scanner(System.in);
            try
            {
                if (!scnr.hasNext("Y") && !scnr.hasNext("y"))
                {
                    folder_directory = scnr.next();
                }
            }
            catch (Exception e)
            {
                System.out.println(e.getStackTrace());
            }
            scnr.close();
            recursionStepAndPrint(folder_directory);
        }
    }

    /**
     * Recursively steps through each file, check for directory call self if
     * directory, otherwise print file path
     * 
     * @param folder_dir : Folder directory re supplied per child directory until
     *                   recursion stops
     */
    private static void recursionStepAndPrint(String folder_dir)
    {
        File _file = new File(folder_dir);
        File[] files = _file.listFiles();
        if (!(files == null))
        {
            for (File file : files)
            {
                if (!file.isDirectory())
                {
                    System.out.println(file.getPath());
                }
                else
                {
                    recursionStepAndPrint(file.getPath());
                }
            }
        }
    }

    /**
     * @return Returns current directory. ".\\" for windows or "./" for Linux
     *         distributions
     */
    private static String getCurrentPath()
    {
        if (System.getProperty("user.dir").indexOf('/') != -1)
        {
            return ".\\";
        }
        else
        {
            return "./";
        }
    }
}
