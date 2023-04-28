

import java.io.IOException;
//import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Servlet2
 */
public class Servlet2 extends HttpServlet {
	private static final long serialVersionUID = 1234624623L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // TODO Auto-generated method stub
//		PrintWriter pwriter = response.getWriter();
		String uname = request.getParameter("nbox");
        String passwd = request.getParameter("pwd");
        System.out.println(uname+"  "+passwd);
        response.getWriter().append("Hello "+uname);
        Cookie c1 = new Cookie("user",uname);
        Cookie c2 = new Cookie("passwd",passwd);
        response.addCookie(c1); 
        response.addCookie(c2);
        
        if(uname.equals("admin")&& passwd.equals("ncuindia")) {
        	response.sendRedirect("servlet");
        }
        else if(uname.equals("std")&& passwd.equals("ncu")) {
        	response.sendRedirect("Servlet3");
        }
        else {
        	response.getWriter().append("Wrong Username or Password");
            response.sendRedirect("index.jsp");
        }
//        response.getWriter().append("Served at: ").append(request.getContextPath());
//        response.sendRedirect("ServletThree");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
