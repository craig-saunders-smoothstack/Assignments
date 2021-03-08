/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingPayment;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface BookingPaymentRepository extends CrudRepository<BookingPayment, Long> {
    public boolean existsById(Integer id);
}
