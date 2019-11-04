package com.javatpoint.controllers;   
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javatpoint.beans.Persons;
import com.javatpoint.dao.PersonDAO;  

@Controller  
public class PersonController {  
	
    @Autowired  
    PersonDAO dao;//will inject dao from xml file  
      
    /*It displays a form to input data, here "command" is a reserved request attribute 
     *which is used to display object data into form 
     */  
    @RequestMapping("/personform")  
    public String showform(Model m){  
    	m.addAttribute("command", new Persons());
    	return "personform"; 
    }  
    /*It saves object into database. The @ModelAttribute puts request data 
     *  into model object. You need to mention RequestMethod.POST method  
     *  because default request is GET*/  
    @RequestMapping(value="/save",method = RequestMethod.POST)  
    public String save(@ModelAttribute("person") Persons person){  
        dao.save(person);  
        return "redirect:/viewperson";//will redirect to viewperson request mapping  
    }  
    /* It provides list of personloyees in model object */  
    @RequestMapping("/viewperson")  
    public String viewperson(Model m){  
        List<Persons> list=dao.getPersons();  
        m.addAttribute("list",list);
        return "viewperson";  
    }  
    /* It displays object data into form for the given id.  
     * The @PathVariable puts URL data into variable.*/  
    @RequestMapping(value="/editperson/{id}")  
    public String edit(@PathVariable int id, Model m){  
        Persons person=dao.getPersonById(id);  
        m.addAttribute("command",person);
        return "personeditform";  
    }  
    /* It updates model object. */  
    @RequestMapping(value="/editsave",method = RequestMethod.POST)  
    public String editsave(@ModelAttribute("person") Persons person){  
        dao.update(person);  
        return "redirect:/viewperson";  
    }  
    /* It deletes record for the given id in URL and redirects to /viewperson */  
    @RequestMapping(value="/deleteperson/{id}",method = RequestMethod.GET)  
    public String delete(@PathVariable int id){  
        dao.delete(id);  
        return "redirect:/viewperson";  
    }   
}  