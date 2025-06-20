

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
//	public static String Load_Driver="com.mysql.cj.jdbc.Driver";
//    public static String URL="jdbc:mysql://localhost:3306/userdb";
//    public static String PASSWORD="root";
//    public static String USERNAME="root";
	Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		connection=MyConnection.getConnection();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		
		
		try {
		PreparedStatement ps=connection.prepareStatement("Select * from admin where username=? And password = ?");
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet r=ps.executeQuery();
		PrintWriter pw=response.getWriter();
		if(r.next()){
//			pw.println("Welcome: "+username);
			response.sendRedirect("home.html");
		}
		else {
			pw.println("User not valid");
			response.sendRedirect("index.html");
		}
		}
		catch(Exception e) {
			e.getMessage();
			
		}
	}

}
