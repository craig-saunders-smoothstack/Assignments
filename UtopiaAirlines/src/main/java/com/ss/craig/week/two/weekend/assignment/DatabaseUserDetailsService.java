/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ss.craig.week.two.weekend.assignment.jpaentities.User;
import com.ss.craig.week.two.weekend.assignment.jpaentities.UserRole;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRoleRepository;

/**
 * @author Craig Saunders
 *
 */
@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository user_repo;

    @Autowired
    private UserRoleRepository user_role_repo;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = user_repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        UserRole user_role = user_role_repo.findById(user.getUserRole().getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Arrays.asList(user_role.getName().split(" ")).stream().forEach(r -> grantedAuthorities.add(new SimpleGrantedAuthority(r)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    } 
}