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

@WebServlet("/update")
public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection connection;
    Truck truck;

    public Update() {
        super();
    }

    public void init(ServletConfig config) throws ServletException {
        connection = MyConnection.getConnection();
        truck = new Truck();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        truck.setTruckNo(request.getParameter("truckNo"));

        try {
            PrintWriter pw = response.getWriter();
            response.setContentType("text/html");

            PreparedStatement ps = connection.prepareStatement("SELECT * FROM truck WHERE truckNo = ?");
            ps.setString(1, truck.getTruckNo());
            ResultSet r = ps.executeQuery();

            if (r.next()) {
                truck = new Truck(
                    r.getString("truckNo"),
                    r.getString("vehicleName"),
                    r.getInt("capacity"),
                    r.getString("driverName")
                );
            }

            pw.println("<!DOCTYPE html>");
            pw.println("<html lang='en'>");
            pw.println("<head>");
            pw.println("    <meta charset='UTF-8'>");
            pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.println("    <title>Update Truck</title>");
            pw.println("    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            pw.println("    <style>");
            pw.println("        body {");
            pw.println("            background-color: aquamarine;");
            pw.println("            background-image:url('https://www.example.com/your-image.jpg');");
            pw.println("            background-size: cover;");
            pw.println("            background-position: center;");
            pw.println("            font-family: Arial, sans-serif;");
            pw.println("            margin: 0;");
            pw.println("            padding: 0;");
            pw.println("            height: 100vh;");
            pw.println("        }");
            pw.println("        header, footer {");
            pw.println("            background-color: rgba(0, 0, 0, 0.7);");
            pw.println("            color: white;");
            pw.println("            text-align: center;");
            pw.println("            padding: 20px;");
            pw.println("        }");
            pw.println("        .form-container {");
            pw.println("            display: flex;");
            pw.println("            justify-content: center;");
            pw.println("            align-items: center;");
            pw.println("            height: 100vh;");
            pw.println("        }");
            pw.println("        .form-card {");
            pw.println("            background-color: rgba(255, 255, 255, 0.9);");
            pw.println("            border-radius: 10px;");
            pw.println("            padding: 3rem;");
            pw.println("            width: 100%;");
            pw.println("            max-width: 500px;");
            pw.println("        }");
            pw.println("        .form-label { font-weight: 600; }");
            pw.println("        .btn-submit { background-color: #4CAF50; color: white; font-size: 18px; border: none; width: 100%; }");
            pw.println("        .btn-submit:hover { background-color: #45a049; }");
            pw.println("    </style>");
            pw.println("</head>");
            pw.println("<body>");

            pw.println("<header><h1>Truck Management System</h1><p>Update Truck Information</p></header>");

            pw.println("<div class='form-container'>");
            pw.println("    <div class='form-card'>");
            pw.println("        <form method='POST' action='update'>");

            pw.println("            <div class='mb-3'>");
            pw.println("                <label for='truckNo' class='form-label'>Truck No:</label>");
            pw.println("                <input type='text' id='truckNo' name='truckNo' class='form-control' value='" + truck.getTruckNo() + "' required>");
            pw.println("            </div>");

            pw.println("            <div class='mb-3'>");
            pw.println("                <label for='vehicleName' class='form-label'>Vehicle Name:</label>");
            pw.println("                <input type='text' id='vehicleName' name='vehicleName' class='form-control' value='" + truck.getVehicleName() + "' required>");
            pw.println("            </div>");

            pw.println("            <div class='mb-3'>");
            pw.println("                <label for='capacity' class='form-label'>Capacity:</label>");
            pw.println("                <input type='text' id='capacity' name='capacity' class='form-control' value='" + truck.getCapacity() + "' required>");
            pw.println("            </div>");

            pw.println("            <div class='mb-3'>");
            pw.println("                <label for='driverName' class='form-label'>Driver Name:</label>");
            pw.println("                <input type='text' id='driverName' name='driverName' class='form-control' value='" + truck.getDriverName() + "' required>");
            pw.println("            </div>");

            pw.println("            <button type='submit' class='btn-submit'>Update</button>");
            pw.println("        </form>");
            pw.println("    </div>");
            pw.println("</div>");

            pw.println("<footer><p>&copy; 2025 Truck Management System. All rights reserved.</p></footer>");
            pw.println("</body>");
            pw.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			PrintWriter pw=response.getWriter();
			//String Truck=request.getParameter("truckNo");
			PreparedStatement ps=connection.prepareStatement("UPDATE truck SET vehicleName=?, capacity=?, driverName=? WHERE truckNo=?\r\n");
			//ps.setString(1,request.getParameter("truckNo"));
			ps.setString(1,request.getParameter("vehicleName"));
			ps.setInt(2,Integer.parseInt(request.getParameter("capacity")));
			ps.setString(3,request.getParameter("driverName"));
			ps.setString(4,request.getParameter("truckNo"));
			ps.executeUpdate();	
			//pw.println(truck);
			 pw.println("<script type='text/javascript'>");
		        pw.println("window.onload = function() {");
		        pw.println("  if (confirm('Data Updated successfully! Click OK to go back.')) {");
		        pw.println("    window.location.href = 'display';"); // Adjust to your previous page
		        pw.println("  }");
		        pw.println("}");
		        pw.println("</script>");
		
	}
		catch(Exception e) {
			e.printStackTrace();
		}
}
    

}
