package com.gontuseries.hellocontroller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice	//this annotation tell MVC framework that class will run for all controller classes to handle exceptions
public class GlobalExceptionHandlerMethods 
{

	//we can replace this class with simply adding configuration in spring-dispatcher-servlet.xml
	//check bean with id="simpleMappingExceptionResolver"

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException(Exception e)
    {
    	//logging Null Pointer Exception
    	System.out.println("Null Pointer Exception Occured: " + e);
    	
    	return "NullPointerException";	//returns view name, here NullPointerException.jsp is view
    }

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = IOException.class)
    public String handleIOException(Exception e)
    {
    	//logging IO Exception
    	System.out.println("IO Exception Occured: " + e);
    	
    	return "IOException";	//returns view name, here IOException.jsp is view
    }

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e)
    {
    	//logging Generic unknown Exception
    	System.out.println("Unknown Exception Occured: " + e);
    	
    	return "Exception";	//returns view name, here Exception.jsp is view
    }

}
