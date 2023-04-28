

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Student_delete_Servlet
 */
public class Student_delete_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_delete_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		html(request,response);
		try {
			delete(request,response);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	static void delete(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uem", "root", "scott");
		response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    PreparedStatement pst = con.prepareStatement("delete from Events where evt_id = ?");
	    String eid_t = request.getParameter("eid");
		int eid=Integer.parseInt(eid_t);
	    pst.setInt(1,eid);
	    int i=pst.executeUpdate();
	    response.setContentType("text/html");
		 if(i == 1) {
				out.print("<h5>Deleted Succesfully!!!</h5>");}
	        else {
				System.out.println("Record not Found!!");
		}
	        try {
	        	 
	            response.setContentType("text/html");
	            PrintWriter out4=response.getWriter();
	            out4.write("<h3>New Event Data</h3>");
	            Statement stmt=con.createStatement();
	            String query="Select * from Events";
	            ResultSet res=stmt.executeQuery(query);
	            out4.print("<table border='1' width'100'");
	            out4.write("<tr><th>evt_Name</th><th>evt_id</th><th>evt_venue</th><th>date</th><th>duration</th><th>organizers</th></tr>");
	            
	            while(res.next()) {
	            	String evt_name=res.getString("evt_name");
	        		String evt_id=res.getString("evt_id");
	        		String evt_venue=res.getString("evt_venue");
	        		String date=res.getString("date");
	        		String duration=res.getString("duration");
	        		String organizers=res.getString("organizers");
	        		out4.print("<tr>"+
	        	    		   "<td>"+evt_name+"</td>"
	        	    		   +"<td>"+evt_id+"</td>"
	        	    		   +"<td>"+evt_venue+"</td>"
	        	    		   +"<td>"+date+"</td>"
	        	    		   +"<td>"+duration+"</td>"
	        	    		   +"<td>"+organizers+"</td></tr>");}
	            
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>NEW DATA</title>\r\n"
				+ "    <legend>DELETE DATA</legend>\r\n"
				+ "<form method=\"post\" action=\"Student_delete_Servlet\">\r\n"
				+ " eid_t : <input type=\"text\" name=\"eid\" /><br /><br />\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
}}
