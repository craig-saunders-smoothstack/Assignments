/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Booking;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {
    public Booking findById(Integer id);
    public boolean existsById(Integer id);
}
