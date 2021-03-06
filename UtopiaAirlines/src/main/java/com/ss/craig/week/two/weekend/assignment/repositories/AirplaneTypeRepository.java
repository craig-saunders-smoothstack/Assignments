/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.AirplaneType;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface AirplaneTypeRepository extends CrudRepository<AirplaneType, Long> {}
