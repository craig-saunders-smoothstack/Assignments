/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Passenger;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface PassengerRepository extends CrudRepository<Passenger, Long> {
    public Passenger findById(Integer id);
    public boolean existsById(Integer id);
}
