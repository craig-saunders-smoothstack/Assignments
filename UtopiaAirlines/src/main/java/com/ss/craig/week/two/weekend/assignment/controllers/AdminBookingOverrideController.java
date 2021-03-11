/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Ticket;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.BookingPaymentRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.PassengerRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.TicketRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminBookingOverrideController extends AdminController{
    
    @Autowired
    private TicketRepository ticket_repo;
    @Autowired
    private BookingRepository booking_repo;
    @Autowired
    private BookingPaymentRepository booking_payment_repo;
    @Autowired
    private PassengerRepository passenger_repo;
    
    @GetMapping(value = "/booking_override")    
    @Transactional(rollbackOn = Exception.class)
    public String adminFlights(
            @RequestParam(name = "action", defaultValue = "cancel") String get_action,
            @RequestParam(name = "object_id", defaultValue = "") String object_id,
            Model model)
    {     
        if (get_action.equals("cancel_id"))
        {        
            model.addAttribute("result_display", "display");            
            if(ticket_repo.existsById(parseIntSafe(object_id)))
            {
                Ticket ticket = ticket_repo.findById(parseIntSafe(object_id));
                ticket_repo.delete(ticket);
                ticket.getBooking().setIsActive((short)0);
                booking_repo.save(ticket.getBooking());
                passenger_repo.delete(ticket.getPassenger());
                ticket.getBookingPayment().setRefunded(true);
                booking_payment_repo.save(ticket.getBookingPayment());                
                model.addAttribute("message", "Ticket "+object_id+" cancelled successfully.");
            }
            else
            {
                model.addAttribute("message", "Ticket does not exist with ID: "+object_id);
            }
        }
        else if (get_action.equals("cancel"))
        {
            model = addIdFormAttributes(model, "ticket", get_action);
            model.addAttribute("field_id", "Ticket ID:");
        }
        return "admin_override";
    }
}