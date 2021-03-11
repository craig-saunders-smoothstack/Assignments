package com.ss.craig.week.two.weekend.assignment;

import javax.sql.DataSource;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UtopiaAirlinesApplication {
    //private static final Logger log = LoggerFactory.getLogger(UtopiaAirlinesApplication.class);
    
	public static void main(String[] args) {
		SpringApplication.run(UtopiaAirlinesApplication.class, args);
	}

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