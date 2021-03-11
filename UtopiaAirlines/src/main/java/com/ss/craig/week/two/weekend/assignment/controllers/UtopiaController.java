/**
 * Controller providing mappings between any requested path and its handler method
 */
package com.ss.craig.week.two.weekend.assignment.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Craig Saunders
 *
 */
@Controller
public class UtopiaController {

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public String index(@RequestParam(name = "name", defaultValue = "World") String name, Model model)
    {
        model.addAttribute("name", name);
        return "greeting";
    }

    @GetMapping(value = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
    public String admin()
    {
        return "adminarea";
    }
/*
    @GetMapping(value = "/register/", produces = MediaType.TEXT_HTML_VALUE)
    public String register()
    {
        return "registration";
    }
*/
    @GetMapping(value = "/user/", produces = MediaType.TEXT_HTML_VALUE)
    public String user()
    {
        return "userarea";
    }

    @GetMapping(value = "/employee/", produces = MediaType.TEXT_HTML_VALUE)
    public String employee()
    {
        return "employeearea";
    }

    @GetMapping(value = "/guest/", produces = MediaType.TEXT_HTML_VALUE)
    public String guest()
    {
        return "guestarea";
    }
}
