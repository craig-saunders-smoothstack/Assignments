/**
 * Wednesday Assignment: Get and display a recursive list of 
 * files and directories from a given directory path
 */
package com.ss.craig.week.one.wednesday.filelistprint;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Craig Saunders
 *
 */
public class ProgramEasyWay {

    public static void main(String[] args)
    {
        printRecursiveFileDirectoryList(args);
    }

    /**
     * Prints the files and folders recursive list from the given path or default
     * current folder
     * 
     * @param args : args string array from main
     */
    private static void printRecursiveFileDirectoryList(String[] args)
    {
        String folder_dir = getCurrentDirectory();
        if (!args.equals(null) && args.length > 0)
        {
            folder_dir = args[0];
        }

        try
        {
            Files.walk(Paths.get(folder_dir)).filter(path -> !Files.isDirectory(path))
                    .forEach(path -> System.out.println(path));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return Returns current directory. ".\\" for windows or "./" for Linux
     *         distributions
     */
    private static String getCurrentDirectory()
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
