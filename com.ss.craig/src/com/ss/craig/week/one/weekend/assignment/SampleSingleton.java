package com.ss.craig.week.one.weekend.assignment;

import org.apache.commons.dbcp2.BasicDataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class SampleSingleton {
    // Final values set here
    private final static String DATABASE_URL = "jdbc:mysql://localhost/test";
    private final static String USER = "root";
    private final static String PASSWORD = "password";
    // BasicDataSource needed for thread-safe singleton
    private static BasicDataSource data_source;
    // Static reference to instance needs to be volatile
    // In order for ALL threads can see it
    private volatile static SampleSingleton instance = null;

    /**
     * Thread safe SampleSingleton instance get function
     * 
     * @return
     */
    public static SampleSingleton getInstance()
    {
        // Single check
        if (instance == null)
        {
            // Lock
            synchronized (SampleSingleton.class)
            {
                // Double check after lock
                if (instance == null)
                {
                    instance = new SampleSingleton();
                    // This is thread safe also for BasicDataSource to be initialized now
                    // Otherwise it would need to be double checked for lock
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(DATABASE_URL);
                    ds.setUsername(USER);
                    ds.setPassword(PASSWORD);

                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    data_source = ds;
                }
            }
        }
        return instance;
    }

    // Private singleton constructor
    private SampleSingleton()
    {
    }

    /**
     * Provides a way to close the data source itself To reopen the pool use the
     * startDataSource method
     * 
     * @throws SQLException
     */
    public static void closeDataSource() throws SQLException
    {
        data_source.close();
    }

    /**
     * Provides a way to start the data source again after closing it
     * 
     * @throws SQLException
     */
    public static void startDataSource() throws SQLException
    {
        data_source.start();
    }

    /**
     * Adds and returns a connection from the thread safe pool
     * 
     * @return Returns new connection from pool
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException
    {
        return data_source.getConnection();
    }

    /**
     * A specific Database Query returning a value rather than doing nothing with it
     * Also throws the sql exceptions that are expected with misuse
     * 
     * @param sql   : The query string
     * @param input : The user input expected to be a BigDecimal
     * @return Returns the result of the computations after query
     * @throws SQLException
     * @throws SQLTimeoutException
     */
    public static BigDecimal databaseQuery(String sql, BigDecimal input) throws SQLException, SQLTimeoutException
    {
        // Gets a connection from a thread safe pool
        // otherwise other threads wouldn't be able to use this function
        // running with the same connection
        Connection conn = SampleSingleton.getConnection();
        ResultSet rs = conn.createStatement().executeQuery(sql); // chains the statement here in between
        BigDecimal x = new BigDecimal(0); // creates a zeroed out BigDecimal
        while (rs.next())
        {
            // While there are rows left, get the next and add
            // the int value of column[1] of the row and multiply
            // the BigDecimal version of it by the input
            x.add((new BigDecimal(rs.getInt(1))).multiply(input));
        }
        // close the no longer needed connection
        conn.close();
        return x;
    }
}
