/**
 * 
 */
package com.ss.craig.week.one.wednesday.appendtextfile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Craig Saunders
 *
 */
public abstract class AppendTextFile {

    /**
     * Appends text specified to the directory specified
     * 
     * @param args : args from static main
     */
    public static void appendText(String[] args)
    {
        String file_path = getStringFromArgs(args, 0);
        if (file_path == null)
        {
            System.out.println("Please provide a file path of an existing file: ");
            Scanner scnr = new Scanner(System.in);
            if (scnr.hasNext())
            {
                file_path = scnr.next();
                scnr.close();
            }
            else
            {
                System.out.println("No valid file path given. Exiting...");
                scnr.close();
                return;
            }
        }
        File file = new File(file_path);
        if (!file.exists())
        {
            // Gives the user friendly option to create a file if it does not exist
            System.out.println("File path given does not exist. Would you like to create it? Y/n");
            Scanner scnr = new Scanner(System.in);
            if (scnr.hasNext() && scnr.next().toLowerCase().equals("y"))
            {
                try
                {
                    file.createNewFile();
                    System.out.println("File created.");
                }
                catch (IOException e)
                {
                    System.out.println("File could not be created. Exiting...");
                    scnr.close();
                    return;
                }
            }
            else
            {
                System.out.println("Exiting...");
                scnr.close();
                return;
            }
            scnr.close();
        }
        String text = getStringFromArgs(args, 1);
        BufferedWriter bw = null;
        try
        {
            bw = new BufferedWriter(new FileWriter(file_path, true));

            if (bw != null)
            {
                bw.append(text);
                System.out.println("Text appended to: " + file_path + " Exiting...");
            }
            bw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("An IO Exception occurred. Exiting...");
        }
    }

    /**
     * @param args  : args from main
     * @param index : specific index
     * @return
     */
    public static String getStringFromArgs(String[] args, int index)
    {
        if (!args.equals(null) && args.length > 0 && !args[index].equals(null))
        {
            return args[index];
        }
        return null;
    }
}
