package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class ChangePassword extends HttpServlet
{
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("link.html").include(request,response);
		//String username = request.getParameter("t1");
		String newpass = request.getParameter("t2");
		String oldpass=request.getParameter("old");
		HttpSession session=request.getSession(false); 

		
			if(session!=null)
			{
				//request.getRequestDispatcher("Home.html").include(request,response);
				
				String name=(String)session.getAttribute("name");
				//if(name.equals(arg0))
				try
				{
					Connection con=DBConnection.getCon();
					PreparedStatement ps=con.prepareStatement("select * from USERLOGINCOOKIE where username=? and password=?");
					ps.setString(1,name);
					ps.setString(2,oldpass);
				    ResultSet rs=ps.executeQuery();
				    if(rs.next())                   //u r an authorised user
				    {
				    	if(!newpass.equals(name))      //if username & new password are different
				    	{
				    		PreparedStatement ps2=con.prepareStatement("update USERLOGINCOOKIE set PASSWORD=?,REPASSWORD=?  where username=?");
				    		ps2.setString(1,newpass);
				    		ps2.setString(2, newpass);
				    		ps2.setString(3,name);
				    		int k=ps2.executeUpdate();
				    		request.getRequestDispatcher("Home.html").include(request,response);
							out.print("<br><br><br><br><br><br><br>Password Successfully Changed.");
				    	}
				    	else
				    	{
				    		//new password is same as username 
				    		request.getRequestDispatcher("user1.html").include(request,response);
				    		
				    	}
				    }
				    else
				    {
				    	//ur old password is nt correct
				    	request.getRequestDispatcher("user2.html").include(request,response);
				    }
				}
				catch(Exception ee)
				{
					ee.printStackTrace();
				}
			}
				
			else
			{
				//out.print("Please Login First");
				request.getRequestDispatcher("Login3.html").include(request,response);
			}
		
		
		out.close();
	}
}

