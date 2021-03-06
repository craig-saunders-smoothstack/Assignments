/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingGuest;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface BookingGuestRepository extends CrudRepository<BookingGuest, Long> {
    public boolean existsById(Integer id);
}
