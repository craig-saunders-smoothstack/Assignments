/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Route;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface RouteRepository extends CrudRepository<Route, Long> {
    public Route findById(Integer id);
}
