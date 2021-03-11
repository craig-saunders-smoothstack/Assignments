/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Craig Saunders
 *
 */
@Configuration
@EnableWebSecurity
public class UtopiaWebSecurityConfig extends WebSecurityConfigurerAdapter {   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/user/**").hasRole("USER")
        .antMatchers("/employee/**").hasRole("EMPLOYEE")
        .antMatchers("/").hasRole("")
        .anyRequest().permitAll()
        .and()
        .logout()
        .logoutSuccessUrl("/")
        .permitAll()
        .and()
        .httpBasic();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

