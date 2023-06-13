package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Add extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	public String TtA;
	public void service(ServletRequest req,ServletResponse res) throws IOException
	{
	  PrintWriter out=res.getWriter();
	  out.println("Hello");
	
	  int Cradit= (int)req.getAttribute("Credit");
	  int Debit= (int)req.getAttribute("Debit");
	  int TA= (int)req.getAttribute("TAmount");
	  String name= (String) req.getAttribute("name");
	  out.println("Customer name"+name);
	  out.println("Debit Amount="+Debit);
	  out.println("crebit Amount="+Cradit);
	   int TtA=TA+Debit;
       out.println("Total_Amount="+TtA);
       int TtA1=TA-Cradit; 
	 
	 
		  
	  
		  
		  try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
				String qurey="UPDATE Customer SET Debit ="+Debit+",Cradit="+Cradit+",TotalAmount="+TtA+" WHERE NAME='"+name+"'";
				 PreparedStatement ps=conn.prepareStatement(qurey);
		          
		            int n=ps.executeUpdate();
		         
		            if(!(n==0))
		         {
		         	 
		         	out.println(" Successful !");
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

