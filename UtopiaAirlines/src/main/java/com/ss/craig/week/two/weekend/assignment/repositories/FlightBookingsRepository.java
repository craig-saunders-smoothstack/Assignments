/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.FlightBookings;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface FlightBookingsRepository extends CrudRepository<FlightBookings, Long> {
    public boolean existsById(Integer id);
}
