/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.ui.Model;

/**
 * @author Craig Saunders
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource( locations = "classpath:application-test.properties" )
class AdminAirportControllerTest extends AdminControllerTest{

    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception
    {
    }
    
    @Test
    void abstractClassTest()
    {
        assertEquals("LETTUCE", capitolizeFirst("LETTUCE"));
        assertEquals("Pickle", capitolizeFirst("pickle"));
        assertEquals("LETTUCE", capitolizeFirst("LETTUCE"));

        assertEquals(-1, parseIntSafe("LETTUCE"));
        assertEquals(-1, parseIntSafe("2pickles"));
        assertEquals(2, parseIntSafe("2"));
        assertEquals(-1, parseIntSafe("2548451864846851351384864698451351584864"));
        assertEquals(-2548451, parseIntSafe("-2548451"));
        assertEquals(0, parseIntSafe("0"));
        assertEquals(-1, parseIntSafe("0.5"));
/*
        Model model = (Model) new HashMap<String, String>();
        model = addChoicesAttributes(model, "A JUICY BURGER");
        assertEquals("display", model.getAttribute("choices_display"));
        assertEquals("What would you like to view/edit?", model.getAttribute("header_text"));
        assertEquals("A JUICY BURGER", model.getAttribute("obj_name"));
        
        model = (Model) new HashMap<String,String>();
        model = addChoicesAttributes(model, "");
        assertEquals("display", model.getAttribute("choices_display"));
        assertEquals("What would you like to view/edit?", model.getAttribute("header_text"));
        assertEquals("", model.getAttribute("obj_name"));

        model = (Model) new HashMap<String,String>();
        model = addIdFormAttributes(model, capitolizeFirst("fries"), "eat");
        assertEquals("display", model.getAttribute("id_form"));
        assertEquals("Choose the Fries to eat", model.getAttribute("header_text"));
        assertEquals("eat_id", model.getAttribute("form_action"));
        assertEquals("Fries", model.getAttribute("obj_name"));
*/
    }

}
