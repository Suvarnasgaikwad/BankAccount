package smp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Debit extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public void service(ServletRequest req,ServletResponse res) throws IOException
	{ Date date=null;
	  PrintWriter out=res.getWriter();
	  date=new Date();
	  java.sql.Date sqldate =new java.sql.Date(date.getTime());
	  int Id=(int)req.getAttribute("Id");
	  int Debit= (int)req.getAttribute("Debit");
	  int TA= (int)req.getAttribute("TAmount");
	  int Cradit=0;
	   int TotalAmount=TA-Debit;
      
 
		  
		  try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
				String qurey="Insert into Entry values(?,?,?,?,?) ";
				 PreparedStatement ps=conn.prepareStatement(qurey);
				    ps.setDate(1, sqldate);
				    ps.setInt(2, Id);
				    ps.setInt(3, Cradit);
					ps.setInt(4, Debit);
					
					ps.setInt(5, TotalAmount);
					
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
			
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","admin");
		 String query="UPDATE Customer SET TotalAmount ='"+TotalAmount+"' WHERE Id= "+Id+"";
		 PreparedStatement ps=conn.prepareStatement(query);
	    int n=ps.executeUpdate();  
	    if(!(n==0))
        {
    
        	out.println(" Ubdate Records Successful !");
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

