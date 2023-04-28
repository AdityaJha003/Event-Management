

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Student_add_Servlet
 */
public class Student_add_Servlet extends HttpServlet {
	private static final long serialVersionUID = 188943L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_add_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		html(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try {
			add(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void add(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uem", "root", "scott");
		response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
		
		
	
		String ename = request.getParameter("ename");
		
		
	
		String eid_t = request.getParameter("eid");
		int eid=Integer.parseInt(eid_t);
		    
		
		String evenue = request.getParameter("evenue");  
		
		
		String date = request.getParameter("date");
		
		
		String duration = request.getParameter("duration");
		
		
		String organizers = request.getParameter("oraganizers");
		    
		String query="insert into Events(evt_name,evt_id,evt_venue,date,duration,organizers) values(?,?,?,?,?,?)";
		
		PreparedStatement pstm =  con.prepareStatement(query);
		
		pstm.setString(1,ename);
		pstm.setInt(2, eid);
		pstm.setString(3, evenue);
		pstm.setString(4, date);
		pstm.setString(5, duration);
		pstm.setString(6, organizers);
		
		pstm.executeUpdate();
		response.setContentType("text/html");
		
		String query1="Select * from Events";
	    Statement stmt=con.createStatement(); 
		ResultSet res=stmt.executeQuery(query1);
		response.setContentType("text/html");
	    PrintWriter out1=response.getWriter();
	    out1.write("<h3>Event Data</h3>");
	    
		out1.print("<table border='1' width'100'");
		out1.write("<tr><th>evt_Name</th><th>evt_id</th><th>evt_venue</th><th>date</th><th>duration</th><th>organizers</th></tr>");
		    
		    while(res.next()) {
		    	String evt_name=res.getString("evt_name");
				String evt_id=res.getString("evt_id");
				String evt_venue=res.getString("evt_venue");
				String Date=res.getString("date");
				String Duration=res.getString("duration");
				String Organizers=res.getString("organizers");
				out1.print("<tr>"+
			    		   "<td>"+evt_name+"</td>"
			    		   +"<td>"+evt_id+"</td>"
			    		   +"<td>"+evt_venue+"</td>"
			    		   +"<td>"+Date+"</td>"
			    		   +"<td>"+Duration+"</td>"
			    		   +"<td>"+Organizers+"</td></tr>");}
			
		}
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>NEW DATA</title>\r\n"
				+ "<form method=\"post\" action=\"Student_add_Servlet\">\r\n"
				+ "  <fieldset>\r\n"
				+ "    <legend>ADD NEW DATA</legend>\r\n"
				+ "    ename : <input type=\"text\" name=\"ename\" /><br /><br />\r\n"
				+ "    eid_t : <input type=\"text\" name=\"eid\" /><br /><br />\r\n"
				+ "    evenue : <input type=\"text\" name=\"evenue\" /><br /><br />\r\n"
				+ "    date : <input type=\"text\" name=\"date\" /><br /><br />\r\n"
				+ "    duration : <input type=\"text\" name=\"duration\" /><br /><br />\r\n"
				+ "    Organizers : <input type=\"text\" name=\"Organizers\" /><br /><br />\r\n"
				+ "  </fieldset>\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
	
}

