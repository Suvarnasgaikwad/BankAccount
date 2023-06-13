package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;


public class List extends HttpServlet 
	{
	private static final long serialVersionUID = 1L;
	public void service(ServletRequest req,ServletResponse res) throws IOException
	{   
		
		PrintWriter out=res.getWriter();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
			String qurey="select*from Customer";
			PreparedStatement ps=conn.prepareStatement(qurey);
			out.println(""+"<table border='3'>"
			           +"<tr><th>Id</th>"+"<th>Name</th>"+"<th>MobileNo</th>"
			           +"<th>Date</th>"
					   +"<th>Total Amount</th>");
			ResultSet rs=ps.executeQuery();
			while(rs.next())
	         {
				int ID=rs.getInt(1);
	         	String name=rs.getString(2); 
	         	String MobileNo=rs.getString(3);
	         	String Date=rs.getString(4);    	
	         	int TotalAmount =rs.getInt(5);
	         	
	         	out.println(""+"<tr><td>"+ID+"</td>"+"<td>"+name+"</td>"+"<td>"+MobileNo+"</td>"
	         	+"<td>"+Date+"</td>"+"<td>"+TotalAmount+"</td>");
	         }
			 ps.close();
		    conn.close();
			}
			catch(Exception e)
			{
				out.println(e);
			}
		out.println(""
	            +"<form action='./Save' method='post'>"
				+"<h1>New Registration Account:</h1>"
	            +"<p>"
				+"<Lable>Enter Customer Id:</Lable>"
			    +"<input type='text'  name='id' placeholder='Enter Customer Id'>"
				+"<p>"
			    +"<lable>Enter Customer Name:</Lable>"
			    +"<input type='text' name='user' placeholder='Enter Customer Name'>"
			    +"<p>"
			    +"<lable>Enter Customer Mobile no.</Lable>"
			    +"<input type='text'name='num'placeholder='Enter Mobile no.'>"
			    +"<p>"
			    +"<input type='submit'value ='submit'>"
			    +"<p>"
			    +"</form>"); 
		
	}
	
	

	}
