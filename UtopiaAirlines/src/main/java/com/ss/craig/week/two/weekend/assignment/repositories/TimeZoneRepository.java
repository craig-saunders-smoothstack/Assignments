/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.TimeZone;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface TimeZoneRepository extends CrudRepository<TimeZone, Long> {
    public TimeZone findById(Integer id);
}
