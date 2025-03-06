package com.interviewmaster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String url = "jdbc:mysql://localhost:3306/db";
	private static final String user = "root";
	private static final String password = "$ury@pratap18sp";
	//keeping it private for control access
	private static Connection connection;
	
	
	//---------------------method to make connection
	public static Connection getConnection() {
		
		//checks if connection is already established or not 
		if(connection==null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(url,user,password);
				
				//System.out.println("Connection Created!");
			    } catch (ClassNotFoundException | SQLException e) {
				   e.printStackTrace();
			    }
		}
		
		return connection;
		
		
		
	}
	
	//---------------------method to close connection
	private static void closeConnection() {
		if(connection!=null) {
			try {
				connection.close();
				connection = null;
				//System.out.println("Connection closed!");
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	//----------------------method so only this class can close the connection
	public static void closeOnExit() {
        closeConnection();
    }
	
	

}
