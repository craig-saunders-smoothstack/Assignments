/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Airport;

/**
 * @author Craig Saunders
 *
 */
@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {
    public Airport findByIataId(String iata_id);
    public boolean existsByIataId(String iata_id);
}
