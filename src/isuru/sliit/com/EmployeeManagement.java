package isuru.sliit.com;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;

public class EmployeeManagement {

	private static Connection connection; 

	public String insertEmployee(String empCode, String empName, String empEmail, String empAge, String empAddress, String empRole, String date) 
	{ 
		String output = ""; 
		try { 
			connection = DBHandler.getDBConnection();
			
			if (connection == null) { 
				return "Error while connecting to the database."; 
			} 
			String query = " insert into employee_tab(`empCode`,`empName`,`empEmail`,`empAge`,`empAddress`,`empRole`,`date`)" 
			+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = connection.prepareStatement(query); 

			preparedStmt.setString(1, empCode); 
			preparedStmt.setString(2, empName); 
			preparedStmt.setString(3, empEmail); 
			preparedStmt.setString(4, empAge); 
			preparedStmt.setString(5, empAddress); 
			preparedStmt.setString(6, empRole); 
			preparedStmt.setString(7, date); 

			preparedStmt.execute(); 
			connection.close(); 
			String newItems = readEmployees(); 
			output = "{\"status\":\"success\", \"data\": \"" + 
			newItems + "\"}"; 
		} 
		catch (Exception e)  { 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the employee's details.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	public String readEmployees() 
	{ 
		String output = ""; 
		try { 
		
			connection = DBHandler.getDBConnection();
			if (connection == null)  { 
				return "Error while connecting to the database."; 
			} 

			output = "<head><link rel='stylesheet' href='Views/main_styles.css'></head><table bemployee='1'><tr><th>Order Code</th><th>Customer ID</th><th>Customer Email</th><th>Customer Name</th><th>Total Amount</th><th>Card Number</th>" + "<th>CVV</th><th>Update</th><th>Remove</th></tr>"; 
			String query = "select * from employee_tab"; 
			Statement stmt = connection.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 

			while (rs.next())  { 
				String employeeID = Integer.toString(rs.getInt("empID")); 
				String employeeCode = rs.getString("empCode");
				String employeeName = rs.getString("empName");
				String employeeEmail = rs.getString("empEmail"); 
				String employeeAge = rs.getString("empAge"); 
				String employeeAddress = rs.getString("empAddress"); 
				String employeeRole = rs.getString("empRole"); 
				String date = rs.getString("date"); 

				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='" + employeeID + "'>" + employeeCode + "</td>"; 
				output += "<td>" + employeeName + "</td>"; 
				output += "<td>" + employeeEmail + "</td>"; 
				output += "<td>" + employeeAge + "</td>"; 
				output += "<td>" + employeeAddress + "</td>"; 
				output += "<td>" + employeeRole + "</td>"; 
				output += "<td>" + date + "</td>"; 

				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-employeeId='" + employeeID + "'>" + "</td></tr>"; 
			
			} 
			connection.close(); 

			output += "</table>"; 
		}  catch (Exception e) { 
			output = "Error while reading the items."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}

	public String updateResearcher(String empID, String empCode, String empName, String empEmail, String empAge, String empAddress, String empRole, String date) 
	 { 
		 String output = ""; 
		 try
		 { 
			 connection = DBHandler.getDBConnection();
			 
		 if (connection == null) 
		 { 
			 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE employee_tab SET empCode=?,empName=?,empEmail=?,empAge=?,empAddress=?,empRole=?,date=? WHERE empID=?"; 
		 PreparedStatement preparedStmt = connection.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, empCode); 
		 preparedStmt.setString(2, empName); 
		 preparedStmt.setString(3, empEmail); 
		 preparedStmt.setString(4, empAge); 
		 preparedStmt.setString(5, empAddress); 
		 preparedStmt.setString(6, empRole); 
		 preparedStmt.setString(7, date); 
		 preparedStmt.setInt(8, Integer.parseInt(empID));
		// execute the statement
		 preparedStmt.execute(); 
		 connection.close(); 
		 String newEmployees = readEmployees(); 
		 output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while updating the employee details.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
	} 

	public String deleteResearcher(String empID) 
	{ 
		
		 String output = ""; 
		 try
		 { 
			 connection = DBHandler.getDBConnection();
			 
		 if (connection == null) 
		 { 
			 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
			 String query = "delete from employee_tab where empID=?"; 
			 PreparedStatement preparedStmt = connection.prepareStatement(query); 
			 preparedStmt.setInt(1, Integer.parseInt(empID)); 
			 preparedStmt.execute(); 
			 connection.close(); 
			 String newEmployees = readEmployees(); 
			 output = "{\"status\":\"success\", \"data\": \"" + newEmployees + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the employee.\"}"; 
			 System.err.println(e.getMessage()); 
		 } 
		 	return output; 
		 } 
	} 

}
