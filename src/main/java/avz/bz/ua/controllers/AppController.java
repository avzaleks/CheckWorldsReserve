package avz.bz.ua.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import avz.bz.ua.dao.Manager;
 

//@Controller
//@RequestMapping("/")
public class AppController {

	
	    @Autowired
	    Manager manager;
	     
	    @Autowired
	    MessageSource messageSource;
	 
	    @RequestMapping(value = "/chwr", method = RequestMethod.GET)
	    public String redirect( @RequestParam("target") String target) {
	        return "redirect:" + target + ".jsp";
	    }
	
	
}
