package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class LoginServlet extends HttpServlet 
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("Home1.html").include(request,response);
		//String name = request.getParameter("name");
		String username = request.getParameter("t1");
		String password = request.getParameter("t2");

		try
		{
			
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("SELECT * FROM USERLOGINCOOKIE where username=? and password=?");
			ps.setString(1,username);
			ps.setString(2,password);
			

			ResultSet rs=ps.executeQuery();
				
			if(rs.next())
			{
				request.getRequestDispatcher("Home1.html").include(request,response);
				out.print("<br><br><br><br><br><br><br>Welcome "+username);
				out.print("<br>You have been sucessfully logged in.");
				HttpSession session=request.getSession();
				session.setAttribute("name", username);
				
				//Cookie ck=new Cookie("name",username);
				//response.addCookie(ck);
			}
			else
			{
				//out.print("Sorry username or password doesnt match");
				request.getRequestDispatcher("Login2.html").include(request,response);
				//out.print("<a href='registration.html'>Register</a>");
			}
		}
		catch (Exception e)
		{
			out.println(e);
		}
		
		out.close();
	}
}
