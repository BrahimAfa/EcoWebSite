package com.eco.DAL;
import java.sql.*;
public class dbConnection {
	private Connection connection;
	private String User ="root";
	private String Password =null;
	private static dbConnection Instance=null;
	private dbConnection() {
		try {
			//no need for this in mysql connector 8.x.x
			//Class.forName("com.mysql.jdbc.Driver"); 
			 connection=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/ecodb",User,Password);  
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection getConnection (){ return connection;}
	
	public static dbConnection getInstance() {
		if(Instance!=null) return Instance;
		Instance = new dbConnection();
		return Instance;
	}
}
