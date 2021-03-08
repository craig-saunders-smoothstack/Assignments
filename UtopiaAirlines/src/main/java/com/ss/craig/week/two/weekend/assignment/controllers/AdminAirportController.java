/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Airport;
import com.ss.craig.week.two.weekend.assignment.repositories.AirportRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.TimeZoneRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminAirportController {
    private final String VIEW_EDIT_STR = "What would you like to view/edit?";
    private final String CREATE_YOUR_STR = "Create your airport:";
    private final String CHOOSE_OBJ_STR = "Choose the airport to ";
    private final String FAILED_STR = " failed: not a valid ";
    private final String OBJECT_STR = "Airport";
    private final String TEMPLATE_STR = "admin_airports";
    
    @Autowired
    private TimeZoneRepository time_zone_repo;
    @Autowired
    private AirportRepository object_repo;
    
    @PostMapping(value = "/airports")
    public String airportSubmit(@ModelAttribute Airport form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id,
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            addPostAttributes(model, form_result, object_id, "Read");
        }
        else if (form_action.equals("update"))
        {
            addPostAttributes(model, form_result, object_id, "Updated");
        }
        else if (form_action.equals("add"))
        {
            addPostAttributes(model, form_result, object_id, "Created");
        }
        else if (form_action.equals("delete"))
        {
            object_repo.delete(object_repo.findByIataId(object_id));
            model = addPostAttributes(model, form_result, object_id, "Deleted");
        }
        return TEMPLATE_STR;
    }
    
    @GetMapping(value = "/airports")
    public String admin_airports(
            @RequestParam(name = "action", defaultValue = "choose") String action,
            @RequestParam(name = "object_id", defaultValue = "") String object_id,
            Model model)
    {
        Airport empty_object = new Airport();
        empty_object.setTimeZone(time_zone_repo.findById(1));     
        if (action.equals("choose"))
        { 
            model = addGetAttributes(model, "none", "block", "none", "none", VIEW_EDIT_STR, empty_object, "", "");
        }
        else if (action.equals("add"))
        { 
            model = addGetAttributes(model, "none", "none", "block", "none", CREATE_YOUR_STR, empty_object, "add", "");
        }
        else if (action.equals("delete_id"))
        {
            if (object_repo.existsByIataId(object_id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Delete your "+OBJECT_STR.toLowerCase()+":", object_repo.findByIataId(object_id), "delete", object_id);
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
            if (object_repo.existsByIataId(object_id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Update your "+OBJECT_STR.toLowerCase()+":", object_repo.findByIataId(object_id), "update", object_id);
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
            if (object_repo.existsByIataId(object_id))
            {
                model = addGetAttributes(model, "none", "none", "block", "none", "Read your "+OBJECT_STR.toLowerCase()+":", object_repo.findByIataId(object_id), "read", object_id);
            }
            else
            {
                model = addGetAttributes(model, "none", "block", "none", "none", "Read "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("read"))
        { 
            model = addGetAttributes(model, "none", "none", "none", "block", CHOOSE_OBJ_STR+"read:", empty_object, "read_id", "");
        }
        return TEMPLATE_STR;
    }
    
    private Model addGetAttributes(Model model, String result_display, String choices_display, String form_display, String id_form_display,
            String header, Airport form_result, String form_action, String object_id)
    {
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
    
    private Model addPostAttributes(Model model, Airport form_result, String object_id, String verb)
    {
        form_result.setTimeZone(time_zone_repo.findById(form_result.getTimeZone().getId()));
        if (form_result != null && form_result.getTimeZone() != null && object_id !="" && object_id.length() == 3)
        {
            Airport result = form_result;
            if (verb.equals("Read") || verb.equals("Updated") || verb.equals("Deleted"))
            {
                result.setIataId(object_id);
                try
                {
                    if (verb.equals("Updated"))
                    {
                        result = object_repo.save(form_result);
                    }
                }
                catch (Exception e)
                {
                    object_id = form_result.getIataId();
                }
            }
            else if (verb.equals("Created"))
            {
                result = object_repo.save(form_result);
            }
            model = addGetAttributes(model, "block", "block", "none", "none", 
                    VIEW_EDIT_STR, result, verb, result.getIataId());
        }
        else
        {                
            model = addGetAttributes(model, "block", "block", "none", "none", 
                VIEW_EDIT_STR, form_result, "Not "+verb, object_id);
        }
        return model;
    }
}