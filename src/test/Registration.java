package test;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class Registration extends HttpServlet
{
	Connection cn;
	PreparedStatement ps;
	Statement st;
	@Override
	public void init()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			cn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","manager");
	        st=cn.createStatement();
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}
	}
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String username=request.getParameter("t1");
		String password=request.getParameter("t2");
		String repassword=request.getParameter("t3");
		String gender=request.getParameter("gender");
		String phone=request.getParameter("t5");
		String email=request.getParameter("t6");

		try
		{
			ResultSet rs=st.executeQuery("select * from userlogincookie where username='"+username+"'");
			if(rs.next())               //duplicate name exists
			{
				//JOptionPane.showMessageDialog(frame,"Username already exists");
				//out.println("<html><body><font color='red' size='5'><center>");
				//out.println("<b>Registration failed..Username already exists</b><br>");
				//out.println("<b><i>Try Again</i></b><br>");
				//out.println(" <a href='http://localhost:1200/Intern_web/regis.html'><i>Click here 2 register again</i></a>");
				//out.println("</center></font></body></html>");
				RequestDispatcher rd=request.getRequestDispatcher("regis1.html");
				rd.include(request, response);

			}
			else
			{
					
				 ps=cn.prepareStatement("INSERT INTO USERLOGINCOOKIE VALUES(?,?,?,?,?,?)");
					ps.setString(1,username);
					ps.setString(2,password);
					ps.setString(3,repassword);
					ps.setString(4,gender);
					ps.setString(5,phone);
					ps.setString(6,email);
					
					int k= ps.executeUpdate();
					
					RequestDispatcher rd=request.getRequestDispatcher("Login.html");
					rd.include(request,response);
						

			}
						
	  }
		  
	  catch(Exception ee)
		{
		  ee.printStackTrace();
	   }
		out.close();
}

}
