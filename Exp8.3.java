// attendance.jsp 
<!DOCTYPE html> 
<html> 
<head> 
<title>Student Attendance</title> 
</head> 
<body> 
<form action="AttendanceServlet" method="post"> 
Student ID: <input type="text" name="studentId" required><br> 
Date: <input type="date" name="date" required><br> 
Status: <select name="status"> 
<option value="Present">Present</option> 
<option value="Absent">Absent</option> 
</select><br> 
<input type="submit" value="Submit"> 
</form> 
</body> 
</html> 
// AttendanceServlet.java 
import java.io.IOException; 
import java.io.PrintWriter; 
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.PreparedStatement; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse; 
@WebServlet("/AttendanceServlet") 
public class AttendanceServlet extends HttpServlet { 
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
String studentId = request.getParameter("studentId"); 
String date = request.getParameter("date"); 
String status = request.getParameter("status"); 
try { 
Class.forName("com.mysql.cj.jdbc.Driver"); 
Connection con = 
DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", 
"password"); 
PreparedStatement ps = con.prepareStatement("INSERT INTO attendance 
(student_id, date, status) VALUES (?, ?, ?)"); 
ps.setString(1, studentId); 
ps.setString(2, date); 
ps.setString(3, status); 
ps.executeUpdate(); 
out.println("Attendance recorded successfully!"); 
con.close(); 
} catch (Exception e) { 
out.println("Error: " + e.getMessage()); 
} 
} 
} 
