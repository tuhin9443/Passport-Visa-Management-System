package com.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class DbUtil {

	public static Connection getConnection(){

		Connection  connection=null;
		ResourceBundle resource=ResourceBundle.getBundle("db");
		String driver=resource.getString("driver");
		String url=resource.getString("url");
		String uname=resource.getString("username");
		String password=resource.getString("password");
		
		try {

			Class.forName(driver);

			connection=DriverManager.getConnection(url,uname,password);
			 
		}catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
		
	}
	
	public static void closeConnection (Connection connection){
		try{
			connection.close();			
		}catch(Exception ex){
			
		}
		
	}
}
