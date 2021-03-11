/**
 * 
 */
package com.ss.craig.week.two.weekend.assignment.tests;

import org.springframework.ui.Model;

/**
 * @author Craig Saunders
 *
 */
abstract class AdminControllerTest {
    
    protected final String VIEW_EDIT_STR = "What would you like to view/edit?";
    protected final String FAILED_STR = "Not a valid ";
    protected final String TEMPLATE_STR = "admin";

    protected Model addChoicesAttributes(Model model, String obj_name)
    {
        model.addAttribute("choices_display", "display");
        model.addAttribute("header_text", VIEW_EDIT_STR);
        model.addAttribute("obj_name", obj_name);
        return model;
    }
    
    protected Model addIdFormAttributes(Model model, String obj_name, String action)
    {
        model.addAttribute("id_form", "display");
        model.addAttribute("header_text", "Choose the "+obj_name+" to "+action);
        model.addAttribute("form_action", action+"_id");
        model.addAttribute("obj_name", obj_name);
        return model;
    }    
    
    protected String capitolizeFirst(String str)
    {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    protected int parseIntSafe(String num)
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
    
}
