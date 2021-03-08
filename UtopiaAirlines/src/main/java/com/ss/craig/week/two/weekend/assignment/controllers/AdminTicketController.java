/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import java.util.Arrays;
import java.util.List;

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

import com.ss.craig.week.two.weekend.assignment.jpaentities.Booking;
import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingPayment;
import com.ss.craig.week.two.weekend.assignment.jpaentities.FlightBookings;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Passenger;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Ticket;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingAgentRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingGuestRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingPaymentRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingUserRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.FlightBookingsRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.PassengerRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.TicketRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminTicketController {
    private final String VIEW_EDIT_STR = "What would you like to view/edit?";
    private final String CREATE_YOUR_STR = "Create your ticket:";
    private final String CHOOSE_OBJ_STR = "Choose the ticket to ";
    private final String FAILED_STR = " failed: not a valid ";
    private final String OBJECT_STR = "Ticket";
    private final String TEMPLATE_STR = "admin_tickets";
    private final String MAPPING_STR = "/tickets";
    
    @Autowired
    private TicketRepository object_repo;
    
    @Autowired
    private BookingRepository booking_repo;
    @Autowired
    private BookingPaymentRepository booking_payment_repo;
    @Autowired
    private BookingAgentRepository booking_agent_repo;
    @Autowired
    private BookingUserRepository booking_user_repo;
    @Autowired
    private BookingGuestRepository booking_guest_repo;
    @Autowired
    private FlightBookingsRepository flight_bookings_repo;
    @Autowired
    private PassengerRepository passenger_repo;
    @Autowired
    private UserRepository user_repo;
    
    @PostMapping(value = MAPPING_STR)
    public String passengerSubmit(@ModelAttribute Ticket form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id,
            @RequestParam(name = "user_id", defaultValue = "")String user_id,
            @RequestParam(name = "guest_email", defaultValue = "")String guest_email,
            @RequestParam(name = "guest_phone", defaultValue = "")String guest_phone,
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            model = addPostAttributes(model, form_result, object_id, user_id, guest_email, guest_phone, "Read");
        }
        else if (form_action.equals("update"))
        {
            model = addPostAttributes(model, form_result, object_id, user_id, guest_email, guest_phone, "Updated");
        }
        else if (form_action.equals("add"))
        {
            model = addPostAttributes(model, form_result, object_id, user_id, guest_email, guest_phone, "Created");
        }
        else if (form_action.equals("delete"))
        {
            object_repo.delete(object_repo.findById(Integer.parseInt(object_id)));
            model = addPostAttributes(model, form_result, object_id, user_id, guest_email, guest_phone, "Deleted");
        }
        return TEMPLATE_STR;
    }
    
    @GetMapping(value = MAPPING_STR)
    public String adminPassengers(
            @RequestParam(name = "action", defaultValue = "choose") String action,
            @RequestParam(name = "object_id", defaultValue = "") String object_id,
            Model model)
    {
        Ticket empty_object = new Ticket();    
        if (action.equals("choose"))
        { 
            model = addGetAttributes(model, Arrays.asList("choices_display"), VIEW_EDIT_STR, empty_object, "", "");
        }
        else if (action.equals("list"))
        {
            model = addListAttributes(model, VIEW_EDIT_STR, (List<Ticket>) object_repo.findAll(), empty_object);
        }
        else if (action.equals("add"))
        { 
            model = addGetAttributes(model, Arrays.asList("form_display"), CREATE_YOUR_STR, empty_object, "add", "");
        }
        else if (action.equals("delete_id"))
        {
            int id = parseIntSafe(object_id);         
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, Arrays.asList("form_display"), "Delete your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "delete", object_id);
            }
            else
            {
                model = addGetAttributes(model, Arrays.asList("choices_display"), "Delete "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("delete"))
        {
            model = addGetAttributes(model, Arrays.asList("id_form_display"), CHOOSE_OBJ_STR+"delete:", empty_object, "delete_id", "");
        }
        else if (action.equals("update_id"))
        {
            int id = parseIntSafe(object_id);
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, Arrays.asList("form_display"), "Update your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "update", object_id);
            }
            else
            {
                model = addGetAttributes(model, Arrays.asList("choices_display"), "Update "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("update"))
        {
            model = addGetAttributes(model, Arrays.asList("id_form_display"), CHOOSE_OBJ_STR+"update:", empty_object, "update_id", "");
        }
        else if (action.equals("read_id"))
        {
            int id = parseIntSafe(object_id);
            if (id > 0 && object_repo.existsById(id))
            {
                model = addGetAttributes(model, Arrays.asList("form_display"), "Read your "+OBJECT_STR.toLowerCase()+":", object_repo.findById(id), "read", object_id);
            }
            else
            {
                model = addGetAttributes(model, Arrays.asList("choices_display"), "Read "+FAILED_STR+" id", empty_object, "choose", "");
            }
        }
        else if (action.equals("read"))
        {
            model = addGetAttributes(model, Arrays.asList("id_form_display"),
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

    private Model addListAttributes(Model model, String header, List<Ticket> all, Ticket obj)
    {        
        model.addAttribute("choices_display", "display");
        model.addAttribute("header_text", header);
        model.addAttribute("form_result", obj);
        model.addAttribute("form_action", "list_all");
        model.addAttribute("obj_list", all);
        model.addAttribute("object_id", "");
        return model;
    }
    
    private Model addGetAttributes(Model model, List<String> displays,
            String header, Ticket form_result, String form_action, String object_id)
    {
        if (form_result.getBooking() == null)
        {
            form_result.setBooking(new Booking());
        }
        if (form_result.getBookingPayment() == null)
        {
            form_result.setBookingPayment(new BookingPayment());
        }
        if (form_result.getFlightBookings() == null)
        {
            form_result.setFlightBookings(new FlightBookings());
        }
        if (form_result.getPassenger() == null)
        {
            form_result.setPassenger(new Passenger());
        }
        displays.stream().forEach(s -> model.addAttribute(s,"display"));
        model.addAttribute("header_text", header);
        model.addAttribute("form_result", form_result);
        model.addAttribute("form_action", form_action);
        model.addAttribute("object_id", object_id);
        return model;        
    }
    
    private Model addPostAttributes(Model model, Ticket form_result, String object_id, String user_id, String guest_email, String guest_phone, String verb)
    {
        if (form_result != null && form_result.getBooking() != null && form_result.getBookingPayment() != null &&
                form_result.getFlightBookings() != null && form_result.getPassenger() != null)
        {
            Booking booking = new Booking();
            if (!booking_repo.existsById(form_result.getBooking().getId()))
            {
                booking = booking_repo.save(form_result.getBooking());
            }
            if (!booking_payment_repo.existsById(form_result.getBookingPayment().getId()))
            {
                form_result.getBookingPayment().setBooking(booking);
                booking_payment_repo.save(form_result.getBookingPayment());
            }  
            if (!flight_bookings_repo.existsById(form_result.getFlightBookings().getId()))
            {
                form_result.getFlightBookings().setBooking(booking);
                flight_bookings_repo.save(form_result.getFlightBookings());
            }  
            if (form_result.getBookingAgent() != null && !booking_agent_repo.existsById(form_result.getBookingAgent().getId()))
            {
                form_result.getBookingAgent().setBooking(booking);
                booking_agent_repo.save(form_result.getBookingAgent());
            } 
            if (user_id != null)
            {
                form_result.getPassenger().setFamilyName(user_repo.findById(parseIntSafe(user_id)).getFamilyName());
                form_result.getPassenger().setGivenName(user_repo.findById(parseIntSafe(user_id)).getGivenName());
            }      
            if (guest_email != null)
            {
                form_result.getBookingGuest().setContactEmail(guest_email);
                form_result.getBookingGuest().setContactPhone(guest_phone);
            }
            if (form_result.getBookingUser() != null && !booking_user_repo.existsById(form_result.getBookingUser().getId()))
            {
                form_result.getBookingUser().setBooking(booking);
                booking_user_repo.save(form_result.getBookingUser());
            } 
            if (form_result.getBookingGuest() != null && !booking_guest_repo.existsById(form_result.getBookingGuest().getId()))
            {
                form_result.getBookingGuest().setBooking(booking);
                booking_guest_repo.save(form_result.getBookingGuest());
            }
            if (!passenger_repo.existsById(form_result.getPassenger().getId()))
            {
                form_result.getPassenger().setBooking(booking);
                passenger_repo.save(form_result.getPassenger());
            }  
            
            Ticket result = form_result;
            if (verb.equals("Read") || verb.equals("Updated") || verb.equals("Deleted"))
            {
                try
                {
                    result.getBooking().setId(parseIntSafe(object_id));
                    if (verb.equals("Updated"))
                    {
                        result = object_repo.save(form_result);
                    }
                }
                catch (Exception e)
                {
                    object_id = Integer.toString(form_result.getBooking().getId());
                }
            }
            else if (verb.equals("Created"))
            {
                result = object_repo.save(form_result);
            }
            model = addGetAttributes(model, Arrays.asList("result_display","choices_display"), 
                    VIEW_EDIT_STR, result, verb, Integer.toString(result.getBooking().getId()));
        }
        else
        {                
            model = addGetAttributes(model, Arrays.asList("result_display","choices_display"), 
                    VIEW_EDIT_STR, form_result, "Not "+verb, object_id);
        }
        return model;
    }    
}
