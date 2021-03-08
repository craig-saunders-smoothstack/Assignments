/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingAgent;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface BookingAgentRepository extends CrudRepository<BookingAgent, Long> {
    public boolean existsById(Integer id);
}
