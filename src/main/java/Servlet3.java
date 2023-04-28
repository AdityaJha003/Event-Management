

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Servlet3
 */
public class Servlet3 extends HttpServlet {
	private static final long serialVersionUID = 11345L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//String rn = request.getParameter("Roll_no");
		try {
			mysql(response, request);
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

	static void mysql(HttpServletResponse response,HttpServletRequest request) throws ClassNotFoundException, SQLException, IOException {
//		String Roll_No=request.getParameter("Rollno");
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/uem", "root", "scott");
    Statement stmt=con.createStatement();
    
    response.getWriter().append("Served at: ").append(request.getContextPath());
    Cookie c[] = request.getCookies();
    System.out.println(c[1].getValue());
    
    if(c[1].getValue().equals("std")&&c[2].getValue().equals("ncu")) {
    String query="Select * from Events";
  //  stmt.setString(1,roll);
    
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    out.write("<h3>Student Data</h3>");
    
    ResultSet res=stmt.executeQuery(query);
    out.print("<table border='1' width'100'");
    out.write("<tr><th>evt_Name</th><th>evt_id</th><th>evt_venue</th><th>date</th><th>duration</th><th>organizers</th></tr>");
    
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
	    		   +"<td>"+organizers+"</td></tr>");

    }
    out.print("Student");
}
	}
	}
