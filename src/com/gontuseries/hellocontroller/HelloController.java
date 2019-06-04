package com.gontuseries.hellocontroller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@Controller
//@RequestMapping("/greet")
public class HelloController //extends AbstractController
{

	@RequestMapping("/welcome/{countryName}/{userName}")
	public ModelAndView helloWorld(@PathVariable Map<String,String> pathVars)//@PathVariable("userName") String name, @PathVariable("countryName") String country)
	{
		String name = pathVars.get("userName");
		String country = pathVars.get("countryName");
		
		ModelAndView modelAndView = new ModelAndView("HelloPage");
		modelAndView.addObject("msg","Hello "+name+". You are from " + country+".");
		
		return modelAndView;
	}
	
	
	/*  @Override 
	  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception 
	  { 
		  ModelAndView modelAndView = new ModelAndView("HelloPage"); modelAndView.addObject("welcomeMessage","Hi User, welcome to the first Spring MVC Application");
	  
		  return modelAndView; 
	  }
	 */
}
