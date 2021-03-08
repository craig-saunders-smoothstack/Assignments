/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Airplane;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
    public Airplane findById(Integer id);
}
