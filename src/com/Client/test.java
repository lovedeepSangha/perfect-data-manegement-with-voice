package com.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
public static void main(String[] args) throws Exception {
	Connection connection=DbConnection.getDBConnection();
	PreparedStatement ps=connection.prepareStatement("SELECT clientno FROM clientreg ORDER BY unique_column DESC LIMIT 1");
	ResultSet rs= ps.executeQuery();
	int sn=rs.getInt("clientno");
	System.out.println(sn);
}
}
