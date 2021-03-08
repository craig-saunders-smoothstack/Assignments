/**
 * DataSource configuration file to make using environment variables easier
 */
package com.ss.craig.week.two.weekend.assignment.config;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Craig Saunders
 *
 */
@Configuration
public class UtopiaConfig {

    @Bean
    public DataSource getDataSource()
    {
        return DataSourceBuilder.create().driverClassName("com.mysql.cj.jdbc.Driver").url(getDataSourceURL())
                .username(System.getenv("SPRING_DB_USER")).password(System.getenv("SPRING_DB_PASS")).build();
    }

    private String getDataSourceURL()
    {        
        return "jdbc:mysql://" + System.getenv("SPRING_DB_HOST") + ":" + System.getenv("SPRING_DB_PORT") + "/"
            + System.getenv("SPRING_DB_NAME");
    }
}
