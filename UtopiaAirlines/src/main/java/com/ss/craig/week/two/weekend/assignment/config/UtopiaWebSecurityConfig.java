/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.config;

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
/*    
    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Hardcoded logins for default login testing purposes
        auth.inMemoryAuthentication()
          .withUser("Craig").password(passwordEncoder().encode("Is#1!ssJAVA-feb-2021")).roles("USER", "EMPLOYEE", "ADMIN")
          .and()
          .withUser("Patrick").password(passwordEncoder().encode("Is#1!ssHEADtrainer2021")).roles("USER", "EMPLOYEE")
          .and()
          .withUser("Akbar").password(passwordEncoder().encode("Is#1!ssTrainer2021")).roles("USER");
    }
*/    
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