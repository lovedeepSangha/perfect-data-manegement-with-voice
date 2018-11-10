import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection{
	static Connection con;
	
	
	public static void main(String[] args) {
		
	}
	
	
	public static Connection getDBConnection()
	{
		try
		{
			                                                                                          
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",
					"garmentprinting", "Megha");
			System.out.println("connected");
			return con;
			
		 }
		catch (Exception e) {
		e.printStackTrace();}
		
		return con;
		

	
	}
}
