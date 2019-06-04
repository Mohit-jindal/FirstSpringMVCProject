<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<body>

	<link rel="stylesheet" href="<spring:theme code='styleSheet'/>" type="text/css" />

	<p>
		<a href="/FirstSpringMVCProject/admissionForm.html?siteTheme=green">Green </a> |
		<a href="/FirstSpringMVCProject/admissionForm.html?siteTheme=red">Red </a>	
	</p>	
	
	<a href="/FirstSpringMVCProject/admissionForm.html?siteLanguage=en">English </a> | 
	<a href="/FirstSpringMVCProject/admissionForm.html?siteLanguage=fr">French </a>
	
	<h1>${headerMessage}</h1>
	
	<h3> <spring:message code="label.admissionForm"/></h3>
	<!-- <h1>STUDENT ADMISSION FORM FOR ENGINEERING COURSE</h1> -->
	
	<form:errors path="student.*" />
	
	<form action="/FirstSpringMVCProject/submitAdmissionForm.html" method="post">
		<table>
			<tr><td><spring:message code="label.studentName" /></td>	<td> <input type="text" name="studentName"/></td></tr>
			<tr><td><spring:message code="label.studentHobby" /></td>	<td> <input type="text" name="studentHobby"/></td></tr>
			<tr><td><spring:message code="label.studentMobile" /></td>	<td> <input type="text" name="studentMobile"/></td></tr>
			<tr><td><spring:message code="label.studentDOB" />  </td>	<td> <input type="text" name="studentDOB"/></td></tr>
			
			<tr><td><spring:message code="label.studentSkills" /></td>	<td> 		<select name="studentSkills" multiple>
																					<option value="Java Core">Java Core</option>
																					<option value="Spring Core">Spring Core</option>
																					<option value="Spring MVC">Spring MVC</option>
																					</select></td></tr>
		</table>
		
		<table>
			<tr><td><spring:message code="label.studentAddress" /></td></tr>
			<tr><td><spring:message code="label.country" /><input type="text" name="studentAddress.country"/></td></tr>
			<tr><td><spring:message code="label.city" /><input type="text" name="studentAddress.city"/></td></tr>
			<tr><td><spring:message code="label.street" /><input type="text" name="studentAddress.street"/></td></tr>
			<tr><td><spring:message code="label.pincode" /><input type="text" name="studentAddress.pincode"/></td></tr>
			<tr>
				<td />
				<td />
			</tr>
			<tr><td><input type="submit" value="Submit this  form by clicking here"/></td></tr>
			
		</table>
	</form>
	
</body>
</html>