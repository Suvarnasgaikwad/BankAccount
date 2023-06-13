package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Check extends HttpServlet 
 	{

	private static final long serialVersionUID = 1L;
	

	public void service(ServletRequest req,ServletResponse res) throws IOException
	{   
		PrintWriter out=res.getWriter();
	
		String Uname=req.getParameter("user");
		String password=req.getParameter("pass");
		out.println("Login"+Uname);
		out.println("Login"+password);
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
		String qurey="select UserName,Password from Customer";
		PreparedStatement ps=conn.prepareStatement(qurey);
		ResultSet rs=ps.executeQuery();
		boolean flag =false;
	     while(rs.next())
	     {
	    	 String user=rs.getString("UserName");
	    	 
	    	 String pass=rs.getString("Password");
	    	 if((Uname.equals(user)) && (password.equals(pass)))
	    	 {
	    		flag=true;
	    		 RequestDispatcher reqd = req.getRequestDispatcher("/Login.html");
	         	 reqd.forward(req, res);
	    		 out.println("Login Successful");
	    		
	    	 }
	
	     }
	     if(!flag)
	 		{
	    	 RequestDispatcher reqd = req.getRequestDispatcher("/Loginuser.html");
         	 reqd.forward(req, res);
	   		 out.println("wrong UserName and Password");
	 		}
         ps.close();
 		conn.close();
 		
 	
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
	}

}
