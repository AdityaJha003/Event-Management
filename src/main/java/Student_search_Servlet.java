

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
 * Servlet implementation class Student_search_Servlet
 */
public class Student_search_Servlet extends HttpServlet {
	private static final long serialVersionUID = 14383L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_search_Servlet() {
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
			search(request,response);
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
	static void search(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uem", "root", "scott");
		response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    PreparedStatement pst = con.prepareStatement("select * from Events where evt_id = ?");
	    String eid_t = request.getParameter("eid");
		int eid=Integer.parseInt(eid_t);
	    pst.setInt(1,eid);
	    out.print("<table border='1' width'100'");
        out.write("<tr><th>evt_Name</th><th>evt_id</th><th>evt_venue</th><th>date</th><th>duration</th><th>organizers</th></tr>");
       
        ResultSet res=pst.executeQuery();
        while(res.next()) {
        	String evt_name=res.getString("evt_name");
    		String evt_id=res.getString("evt_id");
    		String evt_venue=res.getString("evt_venue");
    		String date=res.getString("date");
    		String duration=res.getString("duration");
    		String organizers=res.getString("organizers");
    		out.print("<tr>"+
    	    		   "<td>"+evt_name+"</td>"
    	    		   +"<td>"+evt_id+"</td>"
    	    		   +"<td>"+evt_venue+"</td>"
    	    		   +"<td>"+date+"</td>"
    	    		   +"<td>"+duration+"</td>"
    	    		   +"<td>"+organizers+"</td></tr>");}
	}
    static void html(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>Fetched Data</title>\r\n"
				+ "    <legend>Fetched DATA</legend>\r\n"
				+ "<form method=\"post\" action=\"Student_search_Servlet\">\r\n"
				+ " eid_t : <input type=\"text\" name=\"eid\" /><br /><br />\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
}
	
}
