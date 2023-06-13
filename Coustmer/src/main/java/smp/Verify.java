package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;




public class Verify extends HttpServlet 
	{
	private static final long serialVersionUID = 1L;
	public void service(ServletRequest req,ServletResponse res) throws IOException, ServletException
	{   res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		int Id=Integer.parseInt(req.getParameter("id"));
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
		String qurey=" select*from Customer where Id="+Id;
		PreparedStatement ps=conn.prepareStatement(qurey);
		
		ResultSet rs=ps.executeQuery();
		
			if(rs.next()) 
		  { 
			String name=rs.getString(2);
			String MobileNo=rs.getString(3);
			int TotalAmount=rs.getInt(5);
			req.setAttribute("Name", name);
			req.setAttribute("MobileNo",MobileNo);
			RequestDispatcher reqd = req.getRequestDispatcher("/DAmount.html");
	    	 reqd.include(req, res);
	    	 out.println("Customer Information");
			 out.println("<p>"+" Customer Name: " +name);
			 out.println("<p>"+"Customer Id:"+Id);
			 out.println("<p>"+"Customer Mobile Number:"+MobileNo);
			 out.println("<p>"+"Total Amount:"+TotalAmount);
			
			
	     }
			else 
			{
				RequestDispatcher reqd = req.getRequestDispatcher("/Loginuser.html");
		    	 reqd.forward(req, res);
				 out.println("Records not found for Id="+Id);
			}
         ps.close();
 		conn.close();
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
			String qurey="select*from Entry where Id="+Id;
			PreparedStatement ps=conn.prepareStatement(qurey);
			out.println(""+"<table border='3'>"
			           +"<tr><th>Id</th>"+"<th>Date</th>"+"<th>Cradit</th>"+"<th>Debit</th>"
					   +"<th>Total Amount</th>");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
	         {
	         	
	         	String Date=rs.getString(1);
	         	int ID=rs.getInt(2);
	         	int Cradit =rs.getInt(3);
	         	int Debit =rs.getInt(4);
	         	int TotalAmount =rs.getInt(5);
	         	
	         	out.println(""+"<tr><td>"+Id+"</td>"+"<td>"+Date+"</td>"+"<td>"+Cradit+"</td>"
	         	+"<td>"+Debit+"</td>"+"<td>"+TotalAmount+"</td>");
	         }
			 ps.close();
		    conn.close();
			}
			catch(Exception e)
			{
				out.println(e);
			}
		out.println(""
	            +"<form action='./Search' method='post'>"
			    +"<input type='Hidden' id='Id' name='Id' value='"+Id+"'>"
			    +"<Lable>Enter Debit Amount:</Lable>"
			    +"<input type='number' name='Debit' placeholder='Enter Debit Amount'>"
			    +"<p>"
			    +"<input type='submit'value='submit'>");
        
	}
	

	}
