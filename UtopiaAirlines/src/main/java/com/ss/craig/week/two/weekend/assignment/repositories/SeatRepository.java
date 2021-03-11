/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Airplane;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Seat;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface SeatRepository extends CrudRepository<Seat, Long> {
    public Seat findById(Integer id);
    public boolean existsById(Integer id);
    public List<Seat> findAllByAirplane(Airplane airplane);
}
