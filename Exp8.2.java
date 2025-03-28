<!DOCTYPE html> 
<html> 
<head> 
<title>Search Employee</title> 
</head>  
<body> 
<form action="EmployeeServlet" method="post"> 
Enter Employee ID: <input type="text" name="empId" required> 
<input type="submit" value="Search"> 
</form> 
</body> 
</html> 
// EmployeeServlet.java 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import java.sql.ResultSet; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
@WebServlet("/EmployeeServlet") 
public class EmployeeServlet extends HttpServlet { 
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
String empId = request.getParameter("empId"); 
try { 
Class.forName("com.mysql.cj.jdbc.Driver"); 
Connection con = 
DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", 
"password"); 
PreparedStatement ps = con.prepareStatement("SELECT * FROM employees 
WHERE id = ?"); 
ps.setInt(1, Integer.parseInt(empId)); 
ResultSet rs = ps.executeQuery(); 
if (rs.next()) { 
out.println("<h2>Employee Details</h2>"); 
out.println("<p>ID: " + rs.getInt("id") + "</p>"); 
out.println("<p>Name: " + rs.getString("name") + "</p>"); 
out.println("<p>Department: " + rs.getString("department") + "</p>"); 
} else { 
out.println("<h2>No Employee Found with ID: " + empId + "</h2>"); 
} 
con.close(); 
} catch (Exception e) { 
out.println("Error: " + e.getMessage()); 
} 
} 
} 
