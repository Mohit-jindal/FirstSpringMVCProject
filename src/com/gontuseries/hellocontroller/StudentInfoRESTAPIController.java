package com.gontuseries.hellocontroller;

import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//@Controller
@RestController		//it indicates MVC framework that whatever method you have included inside such a controller class,
					//all such method are basically related to REST API. so here we no need to put @@ResponseBody with this controller class
public class StudentInfoRESTAPIController 
{

		//@ResponseBody	//instruct MVC framework for that controller method don't look for any view technology to prepare final response 
		//instead whatever that controller method return simply convert that to the decide format and send directly to the client
		//here we included jackson* jar files which convert object into equivalent json response
		@RequestMapping(path = "/students", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
		public ArrayList<Student> getStudentList()
		{
			 //here produces argument restrict that this method only produce response in XML format, 
			//if we specify json format in Accept header then this will throw an exception
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
		//@ResponseBody
		@RequestMapping(value = "/students/{name}", method = RequestMethod.GET)
		public Student getStudent(@PathVariable("name") String studentName)
		{
		
			//fetch the student's record using studentName from DB
			Student student = new Student();
			student.setStudentName(studentName);
			student.setStudentHobby("WWE");
			
			return student;
		}

		//*********************Updating A Student Record************************************
/*		@RequestMapping(value = "/students/{name}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public boolean updateStudent(@PathVariable("name") String studentName, @RequestBody Student student)
		{
			//with this consumes argument we have restricted this REST API controller method to work for only those requests
			//where client has specified the message in only XML format, if format is different then exception will throw
			//here @RequestBody annotation map request payload with properties of Student class
			//@RequestBody to work needs to know the type of payload, so we pass type of payload in Content_Type header of request
			//And after checking value of content_type header it according use jackson libraries
			System.out.println("Student Name:" + studentName);
			System.out.println("Student Name:" + student.getStudentName()+", Student Hobby:" + student.getStudentHobby());
			
			//find the matching student record using "studentName" from the DB
			//update the matching student record with the information of student sent by the client
			
			//return true if update is successfully done and return false if update is not done successfully
			
			return true;
		}
*/
		
/*		@RequestMapping(value = "/students/{name}", method = RequestMethod.PUT)
		public ResponseEntity<Void> updateStudent(@PathVariable("name") String studentName, @RequestBody Student student)
		{
			System.out.println("Student Name:" + studentName);
			System.out.println("Student Name:" + student.getStudentName()+", Student Hobby:" + student.getStudentHobby());
			
			//find the matching student record using "studentName" from the DB
			//update the matching student record with the information of student sent by the client
			//by default if there is no exception thrown then method will return 200 response code
			//but e.g for studentName for which we want to update record not found then this method will do nothing but it will
			//not throw any exception, in this case also it will return 200 code which is wrong so
			//we here use ResponseEntity because if for studentName no record found we will send response code 404 manually
			
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	*/
		
		@RequestMapping(value = "/students/{name}", method = RequestMethod.PUT)
		public ResponseEntity<Boolean> updateStudent(@PathVariable("name") String studentName, @RequestBody Student student)
		{
			System.out.println("Student Name:" + studentName);
			System.out.println("Student Name:" + student.getStudentName()+", Student Hobby:" + student.getStudentHobby());
			
			//find the matching student record using "studentName" from the DB
			//we can also pass responsebody in ResponseEntity object as first argument and status code as second argument
			
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Key1", "Value1");
			httpHeaders.add("Key2", "Value2");
			
			return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.OK);
		}

		//*********************Posting A Student Record************************************
		@RequestMapping(value = "/students", method = RequestMethod.POST)
		public ResponseEntity<Boolean> postStudent(@RequestBody Student student)
		{
			System.out.println("Student Name:" + student.getStudentName()+", Student Hobby:" + student.getStudentHobby());
			
			//insert the student record into the database
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add("Location", 
							ServletUriComponentsBuilder.fromCurrentRequest()
												.path("/{name}")
												.buildAndExpand(student.getStudentName()).toUri()
												.toString());
			
			return new ResponseEntity<Boolean>(true, httpHeaders, HttpStatus.CREATED);
		}

		//*********************Deleting A Student Record************************************
		@RequestMapping(value = "/students/{name}", method = RequestMethod.DELETE)
		public ResponseEntity<Boolean> deleteStudent(@PathVariable("name") String studentName)
		{
			System.out.println("Student Name:" + studentName);
			
			//delete the student record from the database
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

		//*********************Deleting all Student Records************************************
		@RequestMapping(value = "/students", method = RequestMethod.DELETE)
		public ResponseEntity<Boolean> deleteAllStudents()
		{
			
			//delete all student records from the database
			
			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}

}
