package isuru.sliit.com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeAPI") 
public class EmployeeAPI extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	EmployeeManagement employeeManagement = new EmployeeManagement();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
			String output = employeeManagement.insertEmployee(
				request.getParameter("empCode"), 
				request.getParameter("empName"), 
				request.getParameter("empEmail"), 
				request.getParameter("empAge"),
				request.getParameter("empAddress"), 
				request.getParameter("empRole"), 
				request.getParameter("date")
			);
				
			response.getWriter().write(output);
		}
	
	private static Map getMap(HttpServletRequest request) {
		
		Map<String, String> map = new HashMap<String, String>(); 
		try { 
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
			String queryString = scanner.hasNext() ? 
			scanner.useDelimiter("\\A").next() : ""; 
			scanner.close(); 
			String[] params = queryString.split("&"); 
			for (String param : params)  {
				String[] p = param.split("=");
				map.put(p[0], p[1]); 
			} 
		} catch (Exception e)  { 
		}
		return map;
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		Map paras = getMap(request); 
		
		String output = employeeManagement.updateEmployeer(paras.get("hidItemIDSave").toString(), 
		paras.get("empCode").toString(), 
		paras.get("empName").toString(), 
		paras.get("empEmail").toString(), 
		paras.get("empAge").toString(), 
		paras.get("empAddress").toString(), 
		paras.get("empRole").toString(), 
		paras.get("date").toString()); 
		response.getWriter().write(output); 
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		Map paras = getMap(request); 
		
		String output = employeeManagement.deleteEmployee(paras.get("employeeID").toString()); 
		response.getWriter().write(output); 
	}

}
