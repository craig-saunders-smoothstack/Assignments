/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

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
public class AdminFlightController extends AdminController{
    private final String OBJECT_STR = "flight";
    private final String[] COLUMN_NAMES = {
            "ID",
            "ROUTE ID",
            "ROUTE ORIGIN",
            "ROUTE DESTINATION",
            "AIRPLANE ID",
            "DEPARTURE TIME",
            "RESERVED SEATS",
            "SEAT PRICE"
    };
    
    @Autowired
    private FlightRepository object_repo;
    @Autowired
    private AirplaneRepository airplane_repo;
    @Autowired
    private RouteRepository route_repo;
    
    @PostMapping(value = "/"+OBJECT_STR+"s")
    public String flightSubmit(@ModelAttribute Flight form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id, 
            @RequestParam(name = "departure_time", defaultValue = "1900-01-01 00:00") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime departure_time,
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            addPostAttributes(model, OBJECT_STR, form_result, departure_time, object_id, "Read");
        }
        else if (form_action.equals("update"))
        {
            addPostAttributes(model, OBJECT_STR, form_result, departure_time, object_id, "Updated");
        }
        else if (form_action.equals("create"))
        {
            addPostAttributes(model, OBJECT_STR, form_result, departure_time, object_id, "Created");
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
            model = addPostAttributes(model, OBJECT_STR, form_result, departure_time, object_id, "Deleted");
        }
        return TEMPLATE_STR;
    }
    
    @GetMapping(value = "/"+OBJECT_STR+"s")
    public String adminFlights(
            @RequestParam(name = "action", defaultValue = "choose") String get_action,
            @RequestParam(name = "object_id", defaultValue = "") String object_id,
            Model model)
    {     
        if (get_action.equals("choose"))
        { 
            model = addChoicesAttributes(model, OBJECT_STR);
        }
        else if (get_action.equals("list"))
        {
            model = addListAttributes(model, OBJECT_STR, StreamSupport.stream(object_repo.findAll().spliterator(), false)
                    .collect(Collectors.toList()), COLUMN_NAMES);
        }
        else if (get_action.equals("read_id") || get_action.equals("update_id") || get_action.equals("delete_id"))
        {
            model = addFormAttributes(model, object_id, OBJECT_STR, get_action.substring(0,get_action.length()-3));
        }
        else if (get_action.equals("create"))
        {
            model = addFormAttributes(model, object_id, OBJECT_STR, get_action);
        }
        else if (get_action.equals("read") || get_action.equals("update") || get_action.equals("delete"))
        {
            model = addIdFormAttributes(model, OBJECT_STR, get_action);
            model.addAttribute("field_id", "Flight ID:");
        }
        return TEMPLATE_STR;
    }
    
    private Model addFormAttributes(Model model, String object_id, String obj_name, String form_action)
    {
        int id = parseIntSafe(object_id);
        if ((id > 0 && object_repo.existsById(id)) || form_action.equals("create"))
        {
            model.addAttribute("form_display", "display");
            model.addAttribute("header_text", capitolizeFirst(form_action)+" your "+obj_name.toLowerCase()+":");
            model.addAttribute("form_action", form_action);
            model.addAttribute("object_id", object_id);
            model.addAttribute("obj_name", obj_name);
            model.addAttribute("form_result", getEmptyObject());
            model = getFormAttributes(model, object_repo.findById(id));
        }
        else
        {

            model = addChoicesAttributes(model, obj_name);
            model.addAttribute("header_text", FAILED_STR+" id");
        }
        return model;
    }
    
    private Model addListAttributes(Model model, String obj_name, List<Flight> all, String[] column_names)
    {       
        addChoicesAttributes(model, obj_name);
        model.addAttribute("form_action", "list_all");
        model.addAttribute("obj_list", all);
        model.addAttribute("column_names", column_names);
        model.addAttribute("all_fields_lists", getAllFieldsLists(all));
        return model;
    }

    private Model addResultAttributes(Model model, String obj_name, Object form_result, String verb, String object_id, boolean successful)
    {      
        model.addAttribute("result_display", "display");
        model.addAttribute("form_result", form_result);
        model.addAttribute("object_id", object_id);
        model.addAttribute("column_names", COLUMN_NAMES);
        
        if (successful)
        {
            
            Map<String, String> fields_map = IntStream.range(0, COLUMN_NAMES.length).boxed()
                    .collect(Collectors.toMap(Arrays.asList(COLUMN_NAMES)::get, getFieldsList((Flight)form_result)::get));
            model.addAttribute("fields_map", fields_map);
            model.addAttribute("form_action", capitolizeFirst(verb)); 
            addChoicesAttributes(model, obj_name);
        }
        else 
        {
            model.addAttribute("form_action", "Not "+capitolizeFirst(verb));
            addChoicesAttributes(model, obj_name);
        }
        return model;
    }

    private Model addPostAttributes(Model model, String obj_name, Flight form_result, LocalDateTime departure_time, String object_id, String verb)
    {
        form_result.setAirplane(airplane_repo.findById(form_result.getAirplane().getId()));
        form_result.setRoute(route_repo.findById(form_result.getRoute().getId()));
        form_result.setDepartureTime(departure_time);
        if (form_result != null && form_result.getAirplane() != null && form_result.getRoute() != null)
        {
            Flight result = form_result;
            boolean is_success = true;
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
                    is_success = false;
                    object_id = Integer.toString(form_result.getId());
                }
            }
            else if (verb.equals("Created"))
            {
                result = object_repo.save(form_result);
            }
            model = addResultAttributes(model, obj_name, result, verb, Integer.toString(result.getId()), is_success);
        }
        else
        {                
            model = addResultAttributes(model, obj_name, form_result, verb, Integer.toString(form_result.getId()), false);
        }
        return model;
    }
    
    private Model getFormAttributes(Model model, Flight flight)
    {
        Map<String, List<String>> fields_map = new HashMap<>();
        if (flight != null && flight.getId() > 0)
        {
            fields_map.put("route.id", Arrays.asList("Route ID:", Integer.toString(flight.getRoute().getId())));
            fields_map.put("airplane.id", Arrays.asList("Airplane ID:", Integer.toString(flight.getAirplane().getId())));
            fields_map.put("reservedSeats", Arrays.asList("Reserved Seats:", Integer.toString(flight.getReservedSeats())));
            fields_map.put("seatPrice", Arrays.asList("Seat Prices:", Float.toString(flight.getSeatPrice())));        
            model.addAttribute("text_inputs", fields_map);
            fields_map = new HashMap<>();
            fields_map.put("departure_time", Arrays.asList("Departure Time:", flight.getDepartureTime().toString()));
            model.addAttribute("date_times", fields_map);
        }
        else
        {
            fields_map.put("route.id", Arrays.asList("Route ID:", ""));
            fields_map.put("airplane.id", Arrays.asList("Airplane ID:", ""));
            fields_map.put("reservedSeats", Arrays.asList("Reserved Seats:", ""));
            fields_map.put("seatPrice", Arrays.asList("Seat Prices:", ""));        
            model.addAttribute("text_inputs", fields_map);
            fields_map = new HashMap<>();
            fields_map.put("departure_time", Arrays.asList("Departure Time:", ""));
            model.addAttribute("date_times", fields_map);
        }
        
        return model;
    }
    
    private List<String> getFieldsList(Flight flight)
    {
        List<String> fields_list = new ArrayList<>();
        fields_list.add(Integer.toString(flight.getId()) );
        fields_list.add(Integer.toString(flight.getRoute().getId()));
        fields_list.add(flight.getRoute().getAirport().getIataId());
        fields_list.add(flight.getRoute().getAirport2().getIataId());
        fields_list.add(Integer.toString(flight.getAirplane().getId()));
        fields_list.add(flight.getDepartureTime().toString());
        fields_list.add(Integer.toString(flight.getReservedSeats()));
        fields_list.add(Float.toString(flight.getSeatPrice()));
        return fields_list;
    }
    
    private List<List<String>> getAllFieldsLists(List<Flight> all)
    {
        List<List<String>> all_fields_list = new ArrayList<>();
        all.stream().forEach(f -> all_fields_list.add(getFieldsList(f)));
        return all_fields_list;
    }
    
    private Flight getEmptyObject()
    {
        Flight f = new Flight();
        f.setRoute(new Route());
        f.setAirplane(new Airplane());
        return f;
    }
}