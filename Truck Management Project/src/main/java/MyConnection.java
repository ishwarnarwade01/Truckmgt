
import java.sql.Connection;
import java.sql.DriverManager;


public class MyConnection{
	 public static String Load_Driver="com.mysql.cj.jdbc.Driver";
	    public static String URL="jdbc:mysql://localhost:3306/truckmgt";
	    public static String PASSWORD="root";
	    public static String USERNAME="root";
	    public static Connection getConnection(){
	        try{
	            Class.forName(Load_Driver);
	            //Making connection
	            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	            
	        }
	        catch (Exception e) {
	            throw new RuntimeException(e);
	        }

	    }
	
}



