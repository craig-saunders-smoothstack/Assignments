/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Ticket;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {
    public Ticket findById(Integer id);
    public boolean existsById(Integer id);
}
