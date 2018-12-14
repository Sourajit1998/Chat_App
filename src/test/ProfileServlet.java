package test;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class ProfileServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
		throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("Home.html").include(request,response);
		
	//	Cookie ck[]=request.getCookies();
		
		HttpSession session=request.getSession(false); 
	try
	{
		
		if(session!=null)
		{
			request.getRequestDispatcher("Home.html").include(request,response);
			//String name=ck[0].getValue();
			String name=(String)session.getAttribute("name");
			
					Connection con=DBConnection.getCon();

					PreparedStatement ps=con.prepareStatement("SELECT * FROM USERLOGINCOOKIE where username=(?)");
					ps.setString(1,name);
					
					ResultSet rs=ps.executeQuery();
				if(rs.next())
				{
					out.print("<html><body>");
					out.print("<table border='3' width='500px'>");
					out.print("<br><br><br><br><br><br><br><caption>Hello !! Welcome to Profile</caption>");
					out.print("<tr><td align='center'>Username: </td><td align='center'>"+name+"</td></tr>");
					out.print("<tr><td align='center'>Password: </td><td align='center'>"+rs.getString(2)+"</td></tr>");
					out.print("<tr><td align='center'>Gender: </td><td align='center'>"+rs.getString(4)+"</td></tr>");
					out.print("<tr><td align='center'>Phone Number: </td><td align='center'>"+rs.getString(5)+"</td></tr>");
					out.print("<tr><td align='center'>Email-id: </td><td align='center'>"+rs.getString(6)+"</td></tr>");
					out.print("</table></body></html>");
					
					
					

					
				}
				else
			{
					out.print("Home");
					//request.getRequestDispatcher("Login3.html").include(request,response);
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
					e.printStackTrace();
				}	
		out.close();
		
	}
}


