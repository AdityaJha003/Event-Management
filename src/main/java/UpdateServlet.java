

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
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			html(request,response);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
		doGet(request, response);}
	static void search(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
	
		String id = request.getQueryString();
		String[] ids = id.split("=");
		System.out.println("Update Servlet: "+ids[2]);
		String param = ids[2];
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
	         
    		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uem","root","scott");
           
    		String ename = request.getParameter("ename");
    		
    		
    		
    		String eid_t = param;
    		int eid=Integer.parseInt(eid_t);
    		    
    		
    		String evenue = request.getParameter("evenue");  
    		
    		
    		String date = request.getParameter("date");
    		
    		
    		String duration = request.getParameter("duration");
    		
    		
    		String organizers = request.getParameter("oraganizers");
    		
            PreparedStatement ps=con.prepareStatement("update Events set evt_name=?, evt_venue=?, date=?, duration=?, oragnizers=? where evt_id = ?");  
            ps.setString(1,ename);  
            ps.setInt(2,eid);  
            ps.setString(3,evenue);  
            ps.setString(4,date);  
            ps.setString(5,duration);
            ps.setString(6, organizers);
              
            ps.executeUpdate();  
            
            String query="Select * from Events";
            //  stmt.setString(1,roll);
              
              response.setContentType("text/html");
              PrintWriter out=response.getWriter();
              out.write("<h3>Student Data</h3>");
              Statement stmt=con.createStatement();
              ResultSet res=stmt.executeQuery(query);
              out.print("<table border='1' width'100'");
              out.write("<tr><th>evt_Name</th><th>evt_id</th><th>evt_venue</th><th>date</th><th>duration</th><th>organizers</th></tr>");
              
              while(res.next()) {
              	String evt_name=res.getString("evt_name");
          		String evt_id=res.getString("evt_id");
          		String evt_venue=res.getString("evt_venue");
          		String Date=res.getString("date");
          		String Duration=res.getString("duration");
          		String Organizers=res.getString("organizers");
          		out.print("<tr>"+
          	    		   "<td>"+evt_name+"</td>"
          	    		   +"<td>"+evt_id+"</td>"
          	    		   +"<td>"+evt_venue+"</td>"
          	    		   +"<td>"+date+"</td>"
          	    		   +"<td>"+duration+"</td>"
          	    		   +"<td>"+organizers+"</td></tr>");}
              
            con.close();  
        }catch(Exception ex){ex.printStackTrace();}
		}

	static void html(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "  <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>\r\n"
				+ "  <title>NEW DATA</title>\r\n"
				+ "    <legend>Updated DATA</legend>\r\n"
				+ "<form method=\"post\" action=\"UpdateServlet\">\r\n"
				+"ename : <input type=\"text\" name=\"ename\" /><br /><br />\r\n"
						+ "    eid_t : <input type=\"text\" name=\"eid\" /><br /><br />\r\n"
						+ "    evenue : <input type=\"text\" name=\"evenue\" /><br /><br />\r\n"
						+ "    date : <input type=\"text\" name=\"date\" /><br /><br />\r\n"
						+ "    duration : <input type=\"text\" name=\"duration\" /><br /><br />\r\n"
						+ "    Organizers : <input type=\"text\" name=\"Organizers\" /><br /><br />\r\n"
				+ "  <input type=\"submit\" value=\"SEND\" />\r\n"
				+ "  <input type=\"reset\" value=\"CLEAR\" />\r\n"
				+ "</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
	}
