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


public class CSearch extends HttpServlet 
	{
	private static final long serialVersionUID = 1L;
	public void service(ServletRequest req,ServletResponse res) throws IOException
	{   
		
         PrintWriter out=res.getWriter();
	
         int Id=Integer.parseInt(req.getParameter("Id"));
		int Cradit=Integer.parseInt(req.getParameter("Cradit"));
		req.setAttribute("Id", Id);
		req.setAttribute("Cradit", Cradit);
		
		
		try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
		String qurey=" select*from Customer where Id="+Id;
		PreparedStatement ps=conn.prepareStatement(qurey);
		
		ResultSet rs=ps.executeQuery();
		while(rs.next())
		{
			
			
			int TA=rs.getInt(5);
			
			out.println("TAmount"+TA);
			req.setAttribute("TAmount", +TA);
			 
	     }
		RequestDispatcher reqd = req.getRequestDispatcher("/Cradit");
    	 reqd.forward(req, res);
		 
         ps.close();
 		conn.close();
		
		}
		catch(Exception e)
		{
			out.println(e);
		}
		

	}
}