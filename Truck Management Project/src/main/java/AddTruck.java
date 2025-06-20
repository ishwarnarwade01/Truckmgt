

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * Servlet implementation class App
 */
@WebServlet("/add")
public class AddTruck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	Scanner sc=new Scanner(System.in);

	Truck truck;
       
  
    public AddTruck() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	public void init(ServletConfig config) throws ServletException {
		connection=MyConnection.getConnection();
		truck=new Truck();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
	    truck = new Truck(
	        request.getParameter("truckNo"),
	        request.getParameter("vehicleName"),
	        Integer.parseInt(request.getParameter("capacity")),
	        request.getParameter("driverName")
	    );

	    try {
	        PrintWriter pw = response.getWriter();
	        response.setContentType("text/html"); // Important for writing HTML content

	        String sql = "INSERT INTO truck (truckNo, vehicleName, capacity, driverName) VALUES (?, ?, ?, ?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, truck.getTruckNo());
	        preparedStatement.setString(2, truck.getVehicleName());
	        preparedStatement.setInt(3, truck.getCapacity());
	        preparedStatement.setString(4, truck.getDriverName());
	        preparedStatement.executeUpdate();

	        // HTML response with popup and redirect
	        pw.println("<html><head><title>Success</title>");
	        pw.println("<script type='text/javascript'>");
	        pw.println("window.onload = function() {");
	        pw.println("  if (confirm('Data added successfully! Click OK to go back.')) {");
	        pw.println("    window.location.href = 'AddTruck.html';"); 
	        pw.println("  }");
	        pw.println("}");
	        pw.println("</script></head><body></body></html>");

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	}


