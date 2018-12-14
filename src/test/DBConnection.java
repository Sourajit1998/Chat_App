package test;
import java.sql.*;



public class DBConnection 
{
	public static Connection con;
	static{
		
			try
			{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
		 }
		 public static Connection getCon()
		{
			 return con;
		}

}