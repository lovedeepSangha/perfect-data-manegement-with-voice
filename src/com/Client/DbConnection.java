package com.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection
{
	 static String path="jdbc:oracle:thin:@localhost:1521:xe";
	 static String user="garmentprinting";
	 
	 static String pass="Megha";
	 static Connection con;
	 
	public static Connection getDBConnection()
	{ 
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
		//	con=DriverManager.getConnection(path,user,pass);
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(path,user,pass);
			return con;
		 }
		catch(ClassNotFoundException e){System.out.println("DB Driver Panga  : "+e);}
		catch(SQLException e){System.out.println("SQL  Panga  : "+e);}
		return con;
	 }
	public static void freeDBConnection(Connection con)
	{
		try
		{
			con.close();
		  }
		catch(Exception e)
		{
			System.out.println("panga in freeDBConnection : "+e);
		  }
	}
}
