package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UsernameServlet
 */
@WebServlet("/UsernameServlet")
public class UsernameServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession(false);
		//String username=Login1.name;
		String username;
		if(session!=null)
		{
			username=(String)session.getAttribute("name");
			out.println("<html>");
			out.println("<head><title>IR |Chat_App</title>");
				out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"style6.css\" >");
			out.println("</head>");	
			out.println("<body>");
				out.println("<div class=\"menu\">");
				out.println("<a href=\"http://www.indianrailways.gov.in/\" target=\"_blank\"><img src=\"Indian-Rail.png\"   title=\"Indian-Railways\" size=\"0\"></a>");
				out.println("<center>");
				out.println("<font color=\"blue\" size=\"3\">");
				out.println("<h1><b>Indian Railways Chat Application</b></h1>");
				out.println("</font>");
			//out.println("<center><br><br><br><br><br><br><br>");
				out.println("<br><br><br>");
			
			out.println("<b>Username: "+username+"</b><br>");
			out.println("<textarea id=\"messagesTextArea\" readonly=\"readonly\" rows=\"15\" cols=\"45\"></textarea><br/>");
			out.println("<input type=\"text\" id=\"messageText\" onkeypress=\"myFunction(event)\" size=\"50\" />");
			out.println("<input type=\"button\" value=\"send\"  onclick=\"sendMessage();\" />");
			
			out.println("<script>");
			out.println("function myFunction(event) {");
			out.println(" var x = event.which; ");
			out.println("if(x==13){sendMessage();}");
			out.println("}");
			out.println("var websocket=new WebSocket(\"ws://192.168.43.11:1200/Chat_App/ChatRoomServerEndPoint\");");
			out.println("websocket.onmessage=function processMessage(message){");
			out.println("var jsonData=JSON.parse(message.data);");
			out.println("if(jsonData.message!=null) messagesTextArea.value+=jsonData.message+\"\\n\";");
			out.println("}");
			out.println("function sendMessage(){");
			out.println("if(messageText.value!=null && messageText.value!='')");
			out.println("{");
			out.println("websocket.send(messageText.value);");
			out.println("messageText.value=\"\";");
			out.println("}");
			out.println("}");
			out.println("</script>");
			
			out.println("</center>");
			
			out.println("</body>");
			out.println("</html>");
			
		}
		else
		{
			request.getRequestDispatcher("Login3.html").include(request,response);
			// out.println("<html><body><font color='red' size='5'><center>");
	       //  out.println("<b>Plz Login First!!!</b><br>");
	        // out.println("</center></font></body></html>");
	        // RequestDispatcher rd=request.getRequestDispatcher("Login_1.html");
	        // rd.include(request, response);
		}
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
