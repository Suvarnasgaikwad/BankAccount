package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Save extends HttpServlet 
 	{

	private static final long serialVersionUID = 1L;
	

	public void service(ServletRequest req,ServletResponse res) throws IOException
	{   
		Date date=null;
		date=new Date();
		java.sql.Date sqldate =new java.sql.Date(date.getTime());
		PrintWriter out=res.getWriter();
		String name=req.getParameter("user");
		String number=req.getParameter("num");
		int Id=Integer.parseInt(req.getParameter("id"));
		int TotalAmount=0000;
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
		String qurey="Insert into Customer values(?,?,?,?,?) ";
		PreparedStatement ps=conn.prepareStatement(qurey);
		ps.setInt(1, Id);
		ps.setString(2, name);
		ps.setString(3, number);
		ps.setDate(4, sqldate);
		ps.setInt(5, TotalAmount);
		int n=ps.executeUpdate();
		 if(!(n==0))
         {
         	 RequestDispatcher reqd = req.getRequestDispatcher("/List");
         	 reqd.include(req, res);
         	out.println(""+"<center>"+" Customer Registration Successful !");
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
