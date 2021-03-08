/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.repositories.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ss.craig.week.two.weekend.assignment.jpaentities.AirplaneType;
import com.ss.craig.week.two.weekend.assignment.repositories.AirplaneTypeRepository;

/**
 * @author Craig Saunders
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AirplaneTypeRepoIntegrationTest {
    
    @Autowired
    private AirplaneTypeRepository airplaneTypeRepository;
   
    @Test
    public void whenCalledSave_thenCorrectNumberOfUsers() {
        int count_before = ((List<AirplaneType>)airplaneTypeRepository.findAll()).size();
        AirplaneType apt = new AirplaneType();
        apt.setMaxCapacity(201);
        airplaneTypeRepository.save(apt);
        int count_after = ((List<AirplaneType>)airplaneTypeRepository.findAll()).size();
        
        assertThat(count_after == count_before+1);
    }  
}
