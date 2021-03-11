/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

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
import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingAgent;
import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingGuest;
import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingPayment;
import com.ss.craig.week.two.weekend.assignment.jpaentities.BookingUser;
import com.ss.craig.week.two.weekend.assignment.jpaentities.FlightBookings;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Passenger;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Ticket;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingAgentRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingGuestRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingPaymentRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingUserRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.FlightBookingsRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.FlightRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.PassengerRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.TicketRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminTicketController extends AdminController{
    //private final String CREATE_YOUR_STR = "Create your ticket:";
    private final String OBJECT_STR = "ticket";
    private final String[] COLUMN_NAMES = {
            "ID",
            "BOOKING ID",
            "BOOKING PAYMENT ID",
            "BOOKING AGENT ID",
            "BOOKING USER ID",
            "BOOKING GUEST ID",
            "FLIGHT BOOKINGS ID",
            "PASSENGER ID"
    };
    
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
    private FlightRepository flight_repo;
    @Autowired
    private PassengerRepository passenger_repo;
    @Autowired
    private UserRepository user_repo;
    
    @PostMapping(value = "/"+OBJECT_STR+"s")
    public String ticketSubmit(@ModelAttribute Ticket form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id,
            @RequestParam(name = "user_id", defaultValue = "")String user_id,
            @RequestParam(name = "guest_email", defaultValue = "")String guest_email,
            @RequestParam(name = "guest_phone", defaultValue = "")String guest_phone,
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            model = addPostAttributes(model, OBJECT_STR, form_result, object_id, "Read");
        }
        else if (form_action.equals("update"))
        {
            model = addPostAttributes(model, OBJECT_STR, form_result, object_id, "Updated");
        }
        else if (form_action.equals("create"))
        {
            model = addPostAttributes(model, OBJECT_STR, form_result, object_id, "Created");
        }
        else if (form_action.equals("delete"))
        {
            object_repo.delete(object_repo.findById(Integer.parseInt(object_id)));
            model = addPostAttributes(model, OBJECT_STR, form_result, object_id, "Deleted");
        }
        return "admin_tickets";
    }
    
    @GetMapping(value = "/"+OBJECT_STR+"s")
    public String adminTickets(
            @RequestParam(name = "action", defaultValue = "choose") String get_action,
            @RequestParam(name = "user_type", defaultValue = "") String user_type,
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
            model = addCreationFormAttributes(model, user_type);
        }
        else if (get_action.equals("read") || get_action.equals("update") || get_action.equals("delete"))
        {
            model = addIdFormAttributes(model, OBJECT_STR, get_action);
            model.addAttribute("field_id", "Ticket ID");
        }
        return "admin_tickets";
    }

    private Model addCreationFormAttributes(Model model, String the_booker)
    {
        model.addAttribute("form_display", "display");
        model.addAttribute("header_text", "Create your "+OBJECT_STR.toLowerCase()+":");
        model.addAttribute("form_action", "create");
        model.addAttribute("obj_name", OBJECT_STR);
        model.addAttribute("form_result", getEmptyObject());
        model = getCreationFormAttributes(model, the_booker);
        return model;
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
    
    private Model addListAttributes(Model model, String obj_name, List<Ticket> all, String[] column_names)
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
                    .collect(Collectors.toMap(Arrays.asList(COLUMN_NAMES)::get, getFieldsList((Ticket)form_result)::get));
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
    
    private Model addPostAttributes(Model model, String obj_name, Ticket form_result, String object_id, String verb)
    {
        if (form_result != null && form_result.getBookingPayment() != null &&
                form_result.getFlightBookings() != null && form_result.getPassenger() != null && 
                (form_result.getBookingAgent() != null || form_result.getBookingGuest() != null || form_result.getBookingUser() != null))
        {
            Ticket result = form_result;
            boolean successful = true;
            if (form_result.getBooking() != null && (verb.equals("Read") || verb.equals("Updated") || verb.equals("Deleted")) && booking_repo.existsById(parseIntSafe(object_id)))
            {
                result.setId(parseIntSafe(object_id));
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
                    successful = false;
                    object_id = Integer.toString(form_result.getBooking().getId());
                }
            }
            else if (verb.equals("Created"))
            {
                try
                {
                    createTicket(form_result);
                }
                catch (Exception e)
                {
                    successful = false;
                    e.printStackTrace();
                }
            }
            model = addResultAttributes(model, obj_name, form_result, verb, Integer.toString(result.getBooking().getId()), successful); 
        }
        else
        {
            model = addResultAttributes(model, obj_name, form_result, "Not "+verb, object_id, false); 
        }
        return model;
    } 
    
    @Transactional(rollbackOn = Exception.class)
    private void createTicket(Ticket ticket) throws Exception
    {
        Booking booking = new Booking();
        booking.setIsActive(ticket.getBooking().getIsActive());
        booking.setConfirmationCode(ticket.getBooking().getConfirmationCode());
        ticket.setBooking(booking_repo.save(booking)); 
        if (ticket.getBookingAgent() != null)
        {
            ticket.getBookingAgent().setUser(user_repo.findById(ticket.getBookingAgent().getUser().getId()));
            ticket.getBookingAgent().setBooking(ticket.getBooking());
            ticket.setBookingAgent(booking_agent_repo.save(ticket.getBookingAgent()));
        } 
        if (ticket.getBookingUser() != null)
        {
            ticket.getBookingUser().setUser(user_repo.findById(ticket.getBookingUser().getUser().getId()));
            ticket.getBookingUser().setBooking(ticket.getBooking());
            ticket.setBookingUser(booking_user_repo.save(ticket.getBookingUser()));
        } 
        else if (ticket.getBookingGuest() != null)
        {
            ticket.getBookingGuest().setBooking(ticket.getBooking());
            ticket.setBookingGuest(booking_guest_repo.save(ticket.getBookingGuest()));            
        } 
        ticket.getBookingPayment().setBooking(ticket.getBooking());
        ticket.setBookingPayment(booking_payment_repo.save(ticket.getBookingPayment()));
        ticket.getFlightBookings().setBooking(ticket.getBooking());
        ticket.getFlightBookings().setFlight(flight_repo.findById(ticket.getFlightBookings().getFlight().getId()));
        ticket.setFlightBookings(flight_bookings_repo.save(ticket.getFlightBookings()));
        if (ticket.getBookingGuest() == null)
        {
            if (ticket.getBookingUser() == null)
            {
                ticket.getPassenger().setGivenName(ticket.getBookingAgent().getUser().getGivenName());
                ticket.getPassenger().setFamilyName(ticket.getBookingAgent().getUser().getFamilyName());
            }
            else
            {
                ticket.getPassenger().setGivenName(ticket.getBookingUser().getUser().getGivenName());
                ticket.getPassenger().setFamilyName(ticket.getBookingUser().getUser().getFamilyName());
            }
        }
        ticket.getPassenger().setBooking(ticket.getBooking());
        ticket.getPassenger().setAirplane(ticket.getFlightBookings().getFlight().getAirplane());
        ticket.setPassenger(passenger_repo.save(ticket.getPassenger()));
        
        object_repo.save(ticket);
    }
    
    private Model getCreationFormAttributes(Model model, String the_booker)
    {
        Map<String, List<String>> fields_map = new HashMap<>();
        fields_map.put("booking.isActive", Arrays.asList("Active:", ""));
        fields_map.put("booking.confirmationCode", Arrays.asList("Confirmation Code:", ""));
        fields_map.put("bookingPayment.refunded", Arrays.asList("Payment Refunded?:", ""));
        fields_map.put("bookingPayment.stripeId", Arrays.asList("Payment Stripe ID:", ""));
        fields_map.put("flightBookings.flight.id", Arrays.asList("Flight ID:", ""));
        fields_map.put("passenger.address", Arrays.asList("Passenger Address:", ""));
        fields_map.put("passenger.dob", Arrays.asList("Passenger Date of Birth:", ""));
        fields_map.put("passenger.gender", Arrays.asList("Passenger Gender:", ""));
        fields_map.put("passenger.seatNumber", Arrays.asList("Passenger Seat Number:", ""));
        fields_map.put("passenger.address", Arrays.asList("Passenger Address:", ""));
        fields_map.put("passenger.dob", Arrays.asList("Passenger Date of Birth:", ""));
        
        if (the_booker.equals("user"))
        {
            fields_map.put("bookingUser.user.id", Arrays.asList("Exisitng Traveler ID:", ""));
        }
        else if (the_booker.equals("guest"))
        {
            fields_map.put("bookingGuest.email", Arrays.asList("Booking Guest Email:", ""));
            fields_map.put("bookingGuest.phone", Arrays.asList("Booking Guest Phone:", ""));
            fields_map.put("passenger.givenName", Arrays.asList("Passenger Given Name:", ""));
            fields_map.put("passenger.familyName", Arrays.asList("Passenger Family Name:", ""));
        }
        else if (the_booker.equals("agent"))
        {
            fields_map.put("bookingAgent.user.id", Arrays.asList("Exisitng Employee ID:", ""));
        }
        model.addAttribute("text_inputs", fields_map);
        return model;
    }
    
    private Model getFormAttributes(Model model, Ticket ticket)
    {
        Map<String, List<String>> fields_map = new HashMap<>();
        if (ticket != null && ticket.getId() > 0)
        {
            fields_map.put("booking.id", Arrays.asList("Booking ID:", Integer.toString(ticket.getBooking().getId())));
            fields_map.put("bookingPayment.id", Arrays.asList("Booking Payment ID:", Integer.toString(ticket.getBookingPayment().getId())));
            if (ticket.getBookingAgent() != null)
            {
                fields_map.put("bookingAgent.id", Arrays.asList("Booking Agent ID:", Integer.toString(ticket.getBookingAgent().getId()))); 
            }
            if (ticket.getBookingUser() != null)
            {
                
                fields_map.put("bookingUser.id", Arrays.asList("Booking User ID:", Integer.toString(ticket.getBookingUser().getId())));
            }
            if (ticket.getBookingGuest() != null)
            {
                fields_map.put("bookingGuest.id", Arrays.asList("Booking Guest ID:", Integer.toString(ticket.getBookingGuest().getId()))); 
            }
            fields_map.put("flightBookings.id", Arrays.asList("Flight Bookings ID:", Integer.toString(ticket.getFlightBookings().getId())));
            fields_map.put("passenger.id", Arrays.asList("Passenger ID:", Integer.toString(ticket.getPassenger().getId())));
            model.addAttribute("text_inputs", fields_map);
        }        
        return model;
    }
    
    private List<String> getFieldsList(Ticket ticket)
    {
        List<String> fields_list = new ArrayList<>();
        fields_list.add(Integer.toString(ticket.getId()) );
        fields_list.add(Integer.toString(ticket.getBooking().getId()));
        fields_list.add(Integer.toString(ticket.getBookingPayment().getId()));
        if (ticket.getBookingAgent() != null)
        {
            fields_list.add(Integer.toString(ticket.getBookingAgent().getId()));
        }
        else
        {
            fields_list.add("");            
        }
        if (ticket.getBookingUser() != null)
        {
            fields_list.add(Integer.toString(ticket.getBookingUser().getId()));
        }
        else
        {
            fields_list.add("");            
        }
        if (ticket.getBookingGuest() != null)
        {
            fields_list.add(Integer.toString(ticket.getBookingGuest().getId()));
        }
        else
        {
            fields_list.add("");            
        }
        fields_list.add(Integer.toString(ticket.getFlightBookings().getId()));
        fields_list.add(Integer.toString(ticket.getPassenger().getId()));
        return fields_list;
    }
    
    private List<List<String>> getAllFieldsLists(List<Ticket> all)
    {
        List<List<String>> all_fields_list = new ArrayList<>();
        all.stream().forEach(f -> all_fields_list.add(getFieldsList(f)));
        return all_fields_list;
    }
    
    private Ticket getEmptyObject()
    {
        Ticket t = new Ticket();
        t.setBooking(new Booking());
        t.setBookingPayment(new BookingPayment());
        t.setBookingAgent(new BookingAgent());
        t.setBookingUser(new BookingUser());
        t.setBookingGuest(new BookingGuest());
        t.setFlightBookings(new FlightBookings());
        t.setPassenger(new Passenger());
        return t;
    }
}
