login.html (Frontend Form) 
<!DOCTYPE html> 
<html> 
<head> 
<title>Login</title> 
</head> 
<body> 
<form action="LoginServlet" method="post"> 
Username: <input type="text" name="username" required><br> 
Password: <input type="password" name="password" required><br> 
<input type="submit" value="Login"> 
</form> 
</body> 
</html> 
// LoginServlet.java (Backend Servlet) 
import java.io.IOException; 
import java.io.PrintWriter; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;  
@WebServlet("/LoginServlet") 
public class LoginServlet extends HttpServlet { 
protected void doPost(HttpServletRequest request, HttpServletResponse response) 
throws ServletException, IOException { 
response.setContentType("text/html"); 
PrintWriter out = response.getWriter(); 
String username = request.getParameter("username"); 
String password = request.getParameter("password"); 
if("admin".equals(username) && "1234".equals(password)) { 
out.println("<h2>Welcome, " + username + "!</h2>"); 
} else { 
out.println("<h2>Invalid Credentials. Please try again.</h2>"); 
} 
} 
} 
