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

import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ss.craig.week.two.weekend.assignment.jpaentities.Booking;
import com.ss.craig.week.two.weekend.assignment.jpaentities.Passenger;
import com.ss.craig.week.two.weekend.assignment.jpaentities.User;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRepository;
import com.ss.craig.week.two.weekend.assignment.repositories.UserRoleRepository;

/**
 * @author Craig Saunders
 *
 */
@Controller
@RequestMapping(path = "/admin/", produces = MediaType.TEXT_HTML_VALUE)
public class AdminUserController extends AdminController{
    private final String OBJECT_STR = "user";
    private final String[] COLUMN_NAMES = {
            "ID",
            "ROLE ID",
            "EMAIL",
            "PHONE",
            "GIVEN NAME",
            "FAMILY NAME",
            "USERNAME"
    };

    @Autowired
    private UserRepository object_repo;
    @Autowired
    private UserRoleRepository user_role_repo; 
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @PostMapping(value = "/"+OBJECT_STR+"s")
    public String userSubmit(@ModelAttribute User form_result, 
            @RequestParam(name = "form_action", defaultValue = "update")String form_action, 
            @RequestParam(name = "object_id", defaultValue = "")String object_id, 
            Model model) throws PropertyValueException
    {
        if (form_action.equals("read"))
        {
            addPostAttributes(model, OBJECT_STR, form_result, object_id, "Read");
        }
        else if (form_action.equals("update"))
        {
            if (!form_result.getPassword().equals("") && !passwordEncoder.matches(form_result.getPassword(), object_repo.findByUsername(form_result.getUsername()).getPassword()))
            {
                form_result.setPassword(passwordEncoder.encode(form_result.getPassword()));
            }
            else
            {
                form_result.setPassword(object_repo.findByUsername(form_result.getUsername()).getPassword());
            }
            addPostAttributes(model, OBJECT_STR, form_result, object_id, "Updated");
        }
        else if (form_action.equals("create"))
        {
            addPostAttributes(model, OBJECT_STR, form_result, object_id, "Created");
        }
        else if (form_action.equals("delete"))
        {
            try
            {
                object_repo.delete(object_repo.findByUsername(object_id));
            }
            catch (Exception e)
            {
            }
            model = addPostAttributes(model, OBJECT_STR, form_result, object_id, "Deleted");
        }
        return TEMPLATE_STR;
    }
    
    @GetMapping(value = "/"+OBJECT_STR+"s")
    public String adminUsers(
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
            model.addAttribute("field_id", "Username");
        }
        return TEMPLATE_STR;
    }
    
    private Model addFormAttributes(Model model, String object_id, String obj_name, String form_action)
    {
        if ((!object_id.equals("") && object_repo.existsByUsername(object_id)) || form_action.equals("create"))
        {
            model.addAttribute("form_display", "display");
            model.addAttribute("header_text", capitolizeFirst(form_action)+" your "+obj_name.toLowerCase()+":");
            model.addAttribute("form_action", form_action);
            model.addAttribute("object_id", object_id);
            model.addAttribute("obj_name", obj_name);
            model.addAttribute("form_result", getEmptyObject());
            model = getFormAttributes(model, object_repo.findByUsername(object_id));
        }
        else
        {
            model = addChoicesAttributes(model, obj_name);
            model.addAttribute("header_text", FAILED_STR+" id");
        }
        return model;
    }
    
    private Model addListAttributes(Model model, String obj_name, List<User> all, String[] column_names)
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
                    .collect(Collectors.toMap(Arrays.asList(COLUMN_NAMES)::get, getFieldsList((User)form_result)::get));
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
    
    private Model addPostAttributes(Model model, String obj_name, User form_result, String object_id, String verb)
    {
        form_result.setUserRole(user_role_repo.findById(form_result.getUserRole().getId()));
        System.out.println(form_result != null && form_result.getUserRole() != null);
        System.out.println(form_result != null);
        System.out.println(form_result.getUserRole() != null);
        System.out.println(form_result);
        System.out.println(form_result.getUserRole());
        if (form_result != null && form_result.getUserRole() != null)
        {
            User result = form_result;
            boolean is_success = true;
            if (verb.equals("Read") || verb.equals("Updated") || verb.equals("Deleted"))
            {
                result.setUsername(object_id);
                try
                {
                    if (verb.equals("Updated") && object_repo.existsByUsername(object_id))
                    {
                        form_result.setId(object_repo.findByUsername(object_id).getId());
                        result = object_repo.save(form_result);
                    }
                }
                catch (Exception e)
                {
                    is_success = false;
                    object_id = form_result.getUsername();
                }
            }
            else if (verb.equals("Created"))
            {
                result = object_repo.save(form_result);
            }
            model = addResultAttributes(model, obj_name, result, verb, result.getUsername(), is_success);
        }
        else
        {                
            model = addResultAttributes(model, obj_name, form_result, verb, form_result.getUsername(), false);
        }
        return model;
    }
    
    private Model getFormAttributes(Model model, User user)
    {
        Map<String, List<String>> fields_map = new HashMap<>();
        if (user != null && !user.getUsername().equals(""))
        {
            fields_map.put("userRole.id", Arrays.asList("User Role:", Integer.toString(user.getUserRole().getId())));
            fields_map.put("email", Arrays.asList("Email:", user.getEmail()));
            fields_map.put("phone", Arrays.asList("Phone:", user.getPhone()));
            fields_map.put("givenName", Arrays.asList("Given Name:", user.getGivenName()));  
            fields_map.put("familyName", Arrays.asList("Family Name:", user.getFamilyName()));    
            fields_map.put("username", Arrays.asList("Username:", user.getUsername()));  
            fields_map.put("password", Arrays.asList("Password:", ""));  
            model.addAttribute("text_inputs", fields_map);
        }
        else
        {
            fields_map.put("userRole.id", Arrays.asList("User Role:", ""));
            fields_map.put("email", Arrays.asList("Email:", ""));
            fields_map.put("phone", Arrays.asList("Phone:", ""));
            fields_map.put("givenName", Arrays.asList("Given Name:", ""));  
            fields_map.put("familyName", Arrays.asList("Family Name:", ""));    
            fields_map.put("username", Arrays.asList("Username:", ""));  
            fields_map.put("password", Arrays.asList("Password:", ""));  
            model.addAttribute("text_inputs", fields_map);
        }
        
        return model;
    }
    
    private List<String> getFieldsList(User user)
    {
        List<String> fields_list = new ArrayList<>();
        fields_list.add(Integer.toString(user.getId()));
        fields_list.add(Integer.toString(user.getUserRole().getId()));
        fields_list.add(user.getEmail());
        fields_list.add(user.getPhone());
        fields_list.add(user.getGivenName());
        fields_list.add(user.getFamilyName());
        fields_list.add(user.getUsername());
        return fields_list;
    }
    
    private List<List<String>> getAllFieldsLists(List<User> all)
    {
        List<List<String>> all_fields_list = new ArrayList<>();
        all.stream().forEach(f -> all_fields_list.add(getFieldsList(f)));
        return all_fields_list;
    }
    
    private Passenger getEmptyObject()
    {
        Passenger a = new Passenger();
        a.setBooking(new Booking());
        return a;
    }
}