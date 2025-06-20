

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

/**
 * Servlet implementation class Delete
 */
@WebServlet("/delete")
public class Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	Truck truck;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Delete() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config) throws ServletException {
		connection = MyConnection.getConnection();
		//truck=new TruckServices1();
		truck=new Truck();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		truck.setTruckNo(request.getParameter("truckNo"));
		try {
		PrintWriter pw=response.getWriter();
		PreparedStatement ps=connection.prepareStatement("delete from truck where truckNo=?");
		ps.setString(1,truck.getTruckNo());
		ps.executeUpdate();	
		
		
		 // HTML response with popup and redirect
        pw.println("<html><head><title>Success</title>");
        pw.println("<script type='text/javascript'>");
        pw.println("window.onload = function() {");
        pw.println("  if (confirm('Data Deleted successfully! Click OK to go back.')) {");
        pw.println("    window.location.href = 'display';"); // Adjust to your previous page
        pw.println("  }");
        pw.println("}");
        pw.println("</script></head><body></body></html>");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
