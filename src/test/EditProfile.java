package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class EditProfile extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("link.html").include(request,response);
		//String username = request.getParameter("t1");
		String newphone = request.getParameter("t5");
		String newemail = request.getParameter("t6");
		HttpSession session=request.getSession(false); 

		try
		{
			if(session!=null)
			{
				//request.getRequestDispatcher("Home.html").include(request,response);
				
				String name=(String)session.getAttribute("name");
			Connection con=DBConnection.getCon();

			PreparedStatement ps=con.prepareStatement("UPDATE USERLOGINCOOKIE SET PHONE=? , EMAIL=? where username=?");
			
			ps.setString(1,newphone);
			ps.setString(2,newemail);
			ps.setString(3,name);
			

			int k=ps.executeUpdate();
			System.out.println(k);
			if(k!=0)
			{
				request.getRequestDispatcher("Home.html").include(request,response);
				
				out.print("<br><br><br><br><br><br><br>User profile Successfully updated.");
			//RequestDispatcher rd=req.getRequestDispatcher("login.html");
			//rd.include(request,response);
			}
			else {
				request.getRequestDispatcher("user.html").include(request,response);
				//out.print("<br><br><br><br><br><br><br>SORRY!! SOMETHING WRONG HAPPENED!!<br>**PLEASE ENTER VALID USERNAME WHICH YOU HAVE ENTERED AT THE TIME OF REGISTRATION !! .");
			}
			}
			else
			{
				//out.print("Please Login First");
				request.getRequestDispatcher("Login3.html").include(request,response);
			}
		}
		catch (Exception e)
		{
			out.println(e);
		}
		
		out.close();
	}
}
