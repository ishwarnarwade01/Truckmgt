

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
 * Servlet implementation class Display
 */
@WebServlet("/display")
public class Display extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;
	Truck truck;
	public void init(ServletConfig config) throws ServletException {
		connection = MyConnection.getConnection();
		//truck=new TruckServices1();
	//truck=new Truck();
	}

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Display() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            String sql = "SELECT * FROM truck";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            pw.println("<!DOCTYPE html>");
            pw.println("<html lang='en'>");
            pw.println("<head>");
            pw.println("    <meta charset='UTF-8'>");
            pw.println("    <meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            pw.println("    <title>Display Trucks</title>");
            pw.println("    <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet'>");
            pw.println("    <style>");
            pw.println("        body {");
            pw.println("            background-color: aquamarine;");
            pw.println("            background-image:  url('https://www.example.com/your-image.jpg'); ");
            pw.println("            background-size: cover;");
            pw.println("            background-position: center;");
            pw.println("            font-family: Arial, sans-serif;");
            pw.println("            color: #333;");
            pw.println("        }");
            pw.println("        header, footer {");
            pw.println("            background-color: rgba(0, 0, 0, 0.7);");
            pw.println("            color: white;");
            pw.println("            text-align: center;");
            pw.println("            padding: 20px;");
            pw.println("        }");
            pw.println("        .container {");
            pw.println("            background-color: rgba(255,255,255,0.9);");
            pw.println("            margin-top: 40px;");
            pw.println("            padding: 30px;");
            pw.println("            border-radius: 12px;");
            pw.println("        }");
            pw.println("        .btn-action { margin-right: 5px; }");
            pw.println("    </style>");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("    <header>");
            pw.println("        <h1>Truck Management System</h1>");
            pw.println("        <p>List of Trucks</p>");
            pw.println("    </header>");
            pw.println("    <div class='container'>");
            pw.println("        <table class='table table-bordered table-striped'>");
            pw.println("            <thead class='table-dark'>");
            pw.println("                <tr>");
            pw.println("                    <th>Truck No</th>");
            pw.println("                    <th>Vehicle Name</th>");
            pw.println("                    <th>Capacity</th>");
            pw.println("                    <th>Driver Name</th>");
            pw.println("                    <th>Action</th>");
            pw.println("                </tr>");
            pw.println("            </thead>");
            pw.println("            <tbody>");

            while (resultSet.next()) {
                String truckNo = resultSet.getString("truckNo");
                String vehicleName = resultSet.getString("vehicleName");
                int capacity = resultSet.getInt("capacity");
                String driverName = resultSet.getString("driverName");

                pw.println("                <tr>");
                pw.println("                    <td>" + truckNo + "</td>");
                pw.println("                    <td>" + vehicleName + "</td>");
                pw.println("                    <td>" + capacity + "</td>");
                pw.println("                    <td>" + driverName + "</td>");
                pw.println("                    <td>");
                pw.println("                        <a href='update?truckNo=" + truckNo + "' class='btn btn-warning btn-sm btn-action'>Update</a>");
                pw.println("                        <a href='delete?truckNo=" + truckNo + "' class='btn btn-danger btn-sm'>Delete</a>");
                pw.println("                    </td>");
                pw.println("                </tr>");
            }

            pw.println("            </tbody>");
            pw.println("        </table>");
            pw.println("    </div>");
            
            pw.println("</body>");
            pw.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
