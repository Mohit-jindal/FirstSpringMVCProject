package com.gontuseries.hellocontroller;

import java.util.ArrayList;
import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL) //here we instruct that whose key value is null please don't include it in Json response
@JsonIgnoreProperties({"studentSkills","studentAddress"})
@JsonPropertyOrder({"studentDOB","student_name","studentMobile","studentAddress","studentHobby","studentSkills"})
public class Student 
{
	@JsonProperty("student_name")	//here we change value of key in Json response
	@Pattern(regexp = "[^0-9]*")
	//@NotEmpty 	//Check if the string is not null or empty //only supported by Hibernate validator library
	private String studentName;
	
	@Size(min = 2, max = 30)// message = "please enter a value for studentHobby field between {min} and {max} characters."
	@IsValidHobby(listOfValidHobbies = "Music|Football|Cricket|Hockey|Marbles")
	private String studentHobby;

	//@Max(2222)	//to restrict value of this field should not be more then 2222
	//	@Min(123)	//Check wheteher the annotated value is higher than or equal to the specified minimum
	private Long studentMobile;
	
	@Past(message = "Value of studentDOB should not be future date") //to check date value not of future
	//@Future	//Checks whether the annotated date is in the future
	private Date studentDOB;
	
	@NotNull	//Check that the annotated value is not null
	private ArrayList<String> studentSkills;
	
	private Address studentAddress;
	
	public Address getStudentAddress() 
	{
		return studentAddress;
	}

	public void setStudentAddress(Address studentAddress) 
	{
		this.studentAddress = studentAddress;
	}

	public Long getStudentMobile() 
	{
		return studentMobile;
	}

	public void setStudentMobile(Long studentMobile) 
	{
		this.studentMobile = studentMobile;
	}

	public Date getStudentDOB() 
	{
		return studentDOB;
	}

	public void setStudentDOB(Date studentDOB) 
	{
		this.studentDOB = studentDOB;
	}

	public ArrayList<String> getStudentSkills() 
	{
		return studentSkills;
	}

	public void setStudentSkills(ArrayList<String> studentSkills) 
	{
		this.studentSkills = studentSkills;
	}

	public String getStudentName() 
	{
		return studentName;
	}
	
	public void setStudentName(String studentName) 
	{
		this.studentName = studentName;
	}
	
	public String getStudentHobby() 
	{
		return studentHobby;
	}
	
	public void setStudentHobby(String studentHobby) 
	{
		this.studentHobby = studentHobby;
	}
	
}
