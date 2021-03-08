/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminController {
    
    @GetMapping(value = "/seats")
    public String admin_seats()
    {
        return "admin_seats";
    }
    @GetMapping(value = "/bookings")
    public String admin_bookings()
    {
        return "admin_bookings";
    }
    @GetMapping(value = "/booking_override")
    public String admin_override()
    {
        return "admin_override";
    }
}
