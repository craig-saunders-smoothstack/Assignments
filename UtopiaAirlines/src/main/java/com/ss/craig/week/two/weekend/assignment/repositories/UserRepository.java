/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.User;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
