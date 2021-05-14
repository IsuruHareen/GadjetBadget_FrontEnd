<%@page import="isuru.sliit.com.EmployeeManagement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GadgetBadget REST Jersey</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/employee.js"></script>
</head>
<body>


	<nav class="navbar navbar-inverse">
	  <div class="container-fluid">
	    <div class="navbar-header">
	      <a class="navbar-brand" href="EmployeeManagement.jsp">GadgetBadget</a>
	    </div>
	    <ul class="nav navbar-nav">
	      <li class=""><a href="EmployeeManagement.jsp">Employee Management</a></li>   
	    </ul>
	  </div>
	</nav>

	<div class="container"><div class="row"><div class="col-6"> 
		<h1 id="h1_header">Employee Management</h1>
		<form id="formEmployee" name="formEmployee">
			 	Employee code: 
			 <input id="empCode" name="empCode" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Employee ID: 
			 <input id="empName" name="empName" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Employee Email: 
			 <input id="empEmail" name="empEmail" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Employee Age: 
			 <input id="empAge" name="empAge" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Employee Address: 
			 <input id="empAddress" name="empAddress" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Employee Role: 
			 <input id="empRole" name="empRole" type="text" 
			 class="form-control form-control-sm">
			 
			 <br> Date: 
			 <input id="date" name="date" type="text" 
			 class="form-control form-control-sm">
			 
			 <br>
			 <input id="btnSave" name="btnSave" type="button" value="Save" 
			 class="btn btn-primary">
			 
			 <input type="hidden" id="hidItemIDSave" 
			 name="hidItemIDSave" value="">
		</form>
		
		<br>
		<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<h2 id="h2_header">All The Employees' Records</h2><br>
				<div id="divItemsGrid">
				 <%
				 	 EmployeeManagement employeeManagement = new EmployeeManagement(); 
					 out.print(employeeManagement.readEmployees());
					 
				 %>
				</div>
			</div> 
		</div> 
	</div>


</body>
</html>