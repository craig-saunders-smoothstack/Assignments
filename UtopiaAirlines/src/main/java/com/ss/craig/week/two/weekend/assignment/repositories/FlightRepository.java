/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Flight;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface FlightRepository extends CrudRepository<Flight, Long> {
    public Flight findById(Integer id);
    public boolean existsById(Integer id);
}
