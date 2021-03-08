/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.UserRole;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    public UserRole findById(Integer id);
}
