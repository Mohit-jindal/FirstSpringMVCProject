package com.gontuseries.hellocontroller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.validation.Valid;
import javax.xml.ws.BindingType;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentAdmissionController
{

	@InitBinder
	public void initBinder(WebDataBinder dataBinder)
	{
		//MVC says whenever you want to customize data binding feature for a controller class
		//MVC supports multiple built-in editor classes
		//dataBinder.setDisallowedFields(new String[] {"studentMobile"});
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		dataBinder.registerCustomEditor(Date.class, "studentDOB", new CustomDateEditor(dateFormat, false));
		dataBinder.registerCustomEditor(String.class, "studentName", new StudentNameEditor());
	}
	
    @RequestMapping (value = "/admissionForm.html", method = RequestMethod.GET)
    public ModelAndView getAdmissionForm() throws Exception
    {
    	/* String exceptionOccured = "NULL_POINTER";
    	if(exceptionOccured.equals("NULL_POINTER"))
    	{
    		throw new NullPointerException("Null Pointer Exception"); //here Spring MVC framework immediately search method which has
    							//annotated with @ExceptionHandler and has value = NullPointerException.class
    	}
    	else if(exceptionOccured.equals("IO_Exception"))
    	{
    		throw new IOException("IO Exception");
    	}
    	else if(exceptionOccured.equals("ARITHMETIC_Exception"))
    	{
    		throw new ArithmeticException("Arithmetic Exception");
    	}
    	*/
        ModelAndView modelAndView = new ModelAndView("AdmissionForm");
        //modelAndView.addObject("headerMessage", "Gontu, College of Engineering, India");
        
        return modelAndView;
    }

/*    @RequestMapping (value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam Map<String, String> mapVars)//@RequestParam (value = "studentName", defaultValue = "Mr. ABC") String name, @RequestParam ("studentHobby") String hobby)
    {
    	String name = mapVars.get("studentName");
    	String hobby = mapVars.get("studentHobby");
    	
        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
        modelAndView.addObject("msg", "Details submitted by you1:: Name: " + name + ", Hobby:" + hobby);

        return modelAndView;
    }
*/
    
   /* @RequestMapping (value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@RequestParam (value = "studentName", defaultValue = "Mr. ABC") String name, @RequestParam ("studentHobby") String hobby)
    {
    	Student student = new Student();
    	student.setStudentName(name);
    	student.setStudentHobby(hobby);
    	
        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
        modelAndView.addObject("headerMessage", "Gontu, College of Engineering, India");
        modelAndView.addObject("student",student);

        return modelAndView;
    }
    */
    
/*    @RequestMapping (value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@ModelAttribute("student") Student student)
    {
    	//This method is same as above method, but here we show power of @ModelAttribute annotation
        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
        //modelAndView.addObject("headerMessage", "Gontu, College of Engineering, India");

        return modelAndView;
    }
  */
    
  /*  @RequestMapping (value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@ModelAttribute("student") Student student, BindingResult result)
    {
    	if(result.hasErrors())
    	{
    		ModelAndView model = new ModelAndView("AdmissionForm");
    		return model;
    	}
    	
        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
        return modelAndView;
    }
*/
    
    @RequestMapping (value = "/submitAdmissionForm.html", method = RequestMethod.POST)
    public ModelAndView submitAdmissionForm(@Valid @ModelAttribute("student") Student student, BindingResult result)
    {
    	//with @Valid annotation with @ModelAttribute we simply instructed Spring MVC framework whenever you performing data binding task
    	//for this Student object its only that time you consider all these form validation related annotations e.g @NotNull which you have kept in Student class
    	//@Valid annotation enables form validation annotations to be consider by framework
    	if(result.hasErrors())
    	{
    		ModelAndView model = new ModelAndView("AdmissionForm");
    		return model;
    	}
    	
        ModelAndView modelAndView = new ModelAndView("AdmissionSuccess");
        return modelAndView;
    }

/*    
    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException(Exception e)
    {
    	//logging Null Pointer Exception
    	System.out.println("Null Pointer Exception Occured: " + e);
    	
    	return "NullPointerException";	//returns view name, here NullPointerException.jsp is view
    }

    @ExceptionHandler(value = IOException.class)
    public String handleIOException(Exception e)
    {
    	//logging IO Exception
    	System.out.println("IO Exception Occured: " + e);
    	
    	return "IOException";	//returns view name, here IOException.jsp is view
    }

    @ExceptionHandler(value = Exception.class)
    public String handleException(Exception e)
    {
    	//logging Generic unknown Exception
    	System.out.println("Unknown Exception Occured: " + e);
    	
    	return "Exception";	//returns view name, here Exception.jsp is view
    }
*/
    @ModelAttribute
    public void addingCommonObjects(Model model)
    {
    	//This method is called before any service method of this controller class. to add common objects on ModelAndView object
    	model.addAttribute("headerMessage", "Gontu, College of Engineering, India");
    }

    //*********************Retrieving All Student Records************************************
 /*   @ResponseBody	//instruct MVC framework for that controller method don't look for any view technology to prepare final response 
    				//instead whatever that controller method return simply convert that to the decide format and send directly to the client
    				//here we included jackson* jar files which convert object into equivalent json response
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    public ArrayList<Student> getStudentList()
    {
    	Student student1 = new Student();
    	student1.setStudentName("The Great Khali");

    	Student student2 = new Student();
    	student2.setStudentName("The Undertaker");
    	
    	Student student3 = new Student();
    	student3.setStudentName("The John Cena");
    	
    	ArrayList<Student> studentsList = new ArrayList<>();
    	
    	studentsList.add(student1);
    	studentsList.add(student2);
    	studentsList.add(student3);

    	return studentsList;
    }
    
  //*********************Retrieving A Student Record************************************
    @ResponseBody
    @RequestMapping(value = "/students/{name}", method = RequestMethod.GET)
    public Student getStudent(@PathVariable("name") String studentName)
    {
    	
    	//fetch the student's record using studentName from DB
    	Student student = new Student();
    	student.setStudentName(studentName);
    	student.setStudentHobby("WWE");
    	
    	return student;
    }
*/
    
}
