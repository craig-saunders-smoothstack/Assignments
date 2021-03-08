/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import java.time.LocalDateTime;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Airplane;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Flight;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Route;
import com.ss.craig.week.two.weekend.assignment.repositories.AirplaneRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.FlightRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.RouteRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminFlightController {
    private final String VIEW_EDIT_STR = "What would you like to view/edit?";
    private final String CREATE_YOUR_STR = "Create your flight:";
    private final String CHOOSE_OBJ_STR = "Choose the flight to ";
    private final String FAILED_STR = " failed: not a valid ";
    private final String OBJECT_STR = "Flight";
    private final String TEMPLATE_STR = "admin_flights";
    private final String MAPPING_STR = "/flights";
    
    @Autowired
    private FlightRepository object_repo;
    @Autowired
    private AirplaneRepository airplane_repo;
    @Autowired
    private RouteRepository route_repo;
    
    @PostMapping(value = MAPPING_STR)
    public String flightSubmit(@ModelAttribute Flight form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id, 
            @RequestParam(name = "departure_time", defaultValue = "1900-01-01T00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime departure_time,
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            addPostAttributes(model, form_result, departure_time, object_id, "Read");
        }
        else if (form_action.equals("update"))
        {
            addPostAttributes(model, form_result, departure_time, object_id, "Updated");
        }
        else if (form_action.equals("add"))
        {
            addPostAttributes(model, form_result, departure_time, object_id, "Created");
        }
        else if (form_action.equals("delete"))
        {
            try
            {
                object_repo.delete(object_repo.findById(parseIntSafe(object_id)));
            }
            catch (Exception e)
            {
            }
            model = addPostAttributes(model, form_result, departure_time, object_id, "Deleted");
        }
        return TEMPLATE_STR;
    }
    
    @GetMapping(value = MAPPING_STR)
    public String admin_flights(
            @RequestParam(name = "action", defaultValue = "choose") String action,
            @RequestParam(name = "object_id", defaultValue = "") String object_id,
            Model model)
    {
        Flight empty_object = new Flight();       
        if (action.equals("choose"))
        { 
            model = addGetAttributes(model, "none", "block", "none", "none",
                    VIEW_EDIT_STR, empty_object,
                    "", "");
        }
        else if (action.equals("add"))
        { 
            model = addGetAttributes(model, "none", "none", "block", "none", CREATE_YOUR_STR, empty_object, "add", "");
        }
        else if (action.equals("delete_id"))
        {
            int id = parseIntSafe(object_id);
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Delete your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "delete", object_id);
            }
            else
            {
                model = addGetAttributes(model, "none", "block", "none", "none", "Delete "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("delete"))
        {
            model = addGetAttributes(model, "none", "none", "none", "block", CHOOSE_OBJ_STR+"delete:", empty_object, "delete_id", "");
        }
        else if (action.equals("update_id"))
        {
            int id = parseIntSafe(object_id);
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Update your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "update", object_id);
            }
            else
            {
                model = addGetAttributes(model, "none", "block", "none", "none", "Update "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("update"))
        {
            model = addGetAttributes(model, "none", "none", "none", "block", CHOOSE_OBJ_STR+"update:", empty_object, "update_id", "");
        }
        else if (action.equals("read_id"))
        {
            int id = parseIntSafe(object_id);
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Read your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "read", object_id);
            }
            else
            {
                model = addGetAttributes(model, "none", "block", "none", "none", "Read "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("read"))
        {
            model = addGetAttributes(model, "none", "none", "none", "block",
                    CHOOSE_OBJ_STR+"read:", empty_object, "read_id", "");
        }
        return TEMPLATE_STR;
    }
    
    private int parseIntSafe(String num)
    {
        int id = 0;
        try
        {
            id = Integer.parseInt(num);
        }
        catch(Exception e)
        {   
            id = -1;
        } 
        return id;
    }
    
    private Model addGetAttributes(Model model, String result_display, String choices_display, String form_display, String id_form_display,
            String header, Flight form_result, String form_action, String object_id)
    {
        if (form_result.getAirplane() == null)
        {
            form_result.setAirplane(new Airplane());
        }
        if (form_result.getRoute() == null)
        {
            form_result.setRoute(new Route());
        }
        model.addAttribute("result_display", result_display);
        model.addAttribute("choices_display", choices_display);
        model.addAttribute("form_display", form_display);
        model.addAttribute("id_form_display", id_form_display);
        model.addAttribute("header_text", header);
        model.addAttribute("form_result", form_result);
        model.addAttribute("form_action", form_action);
        model.addAttribute("object_id", object_id);
        return model;        
    }
    
    private Model addPostAttributes(Model model, Flight form_result, LocalDateTime departure_time, String object_id, String verb)
    {
        form_result.setAirplane(airplane_repo.findById(form_result.getAirplane().getId()));
        form_result.setRoute(route_repo.findById(form_result.getRoute().getId()));
        form_result.setDepartureTime(departure_time);
        if (!(form_result.getAirplane() == null) && !(form_result.getRoute() == null))
        {
            Flight result = form_result;
            if (verb.equals("Read") || verb.equals("Updated") || verb.equals("Deleted"))
            {
                try
                {
                    result.setId(parseIntSafe(object_id));
                    if (verb.equals("Updated"))
                    {
                        result = object_repo.save(form_result);
                    }
                }
                catch (Exception e)
                {
                    object_id = Integer.toString(form_result.getId());
                }
            }
            else if (verb.equals("Created"))
            {
                result = object_repo.save(form_result);
            }
            model = addGetAttributes(model, "block", "block", "none", "none", 
                    VIEW_EDIT_STR, result, verb, Integer.toString(result.getId()));
        }
        else
        {                
            model = addGetAttributes(model, "block", "block", "none", "none", 
                    VIEW_EDIT_STR, form_result, "Not "+verb, object_id);
        }
        return model;
    }
    
}