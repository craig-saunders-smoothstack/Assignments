/**
 * Wednesday Assignment: Count specified character or pattern in a file
 */
package com.ss.craig.week.one.wednesday.charcount;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;

import com.ss.craig.week.one.wednesday.appendtextfile.AppendTextFile;

/**
 * @author Craig Saunders
 *
 */
public class CharacterCount {

    private static Long count = (long) 0;
    /**
     * @param args @args[0] : file path @args[1] : search regex string
     */
    public static void main(String[] args)
    {
        String file_path = AppendTextFile.getStringFromArgs(args, 0);
        String look_for = AppendTextFile.getStringFromArgs(args, 1);

        if (!file_path.equals(null))
        {
            if (!look_for.equals(null))
            {
                File file = new File(file_path);
                if (file.exists())
                {
                    BufferedReader br = null;
                    try
                    {
                        br = new BufferedReader(new FileReader(file_path));
                    }
                    catch (FileNotFoundException e)
                    {
                        // this shouldn't be reached as we checked if file exists first
                        System.out.println("The file was not found");
                    }
                    Pattern pattern = Pattern.compile(look_for);
                    br.lines().forEach(str -> addToCount(pattern.splitAsStream(str).count()));
                    System.out.println("The amount of occurances of pattern provided: " + Long.toString(count));
                    try
                    {
                        br.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                System.out.println("You must provide a search character or regex string. Exiting...");
            }
        }
        else
        {
            System.out.println("You must provide a file path. Exiting...");
        }
    }
    
    /** adds count per line to static count
     * @param amount : Count per line
     */
    private static void addToCount(Long amount)
    {
        count += amount;
    }
}
