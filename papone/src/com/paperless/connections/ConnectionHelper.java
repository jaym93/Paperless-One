package com.paperless.connections;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionHelper {
	Connection conn;
	public  Connection open() throws Exception{
		try{
			
			Properties props = new Properties();
			   FileInputStream in = 
			      new FileInputStream("C:\\paperless.properties");
			   props.load(in);
			   in.close();
			   Class.forName("com.mysql.jdbc.Driver");
			   String url = props.getProperty("url");
			   String username = props.getProperty("user");
			   String password = props.getProperty("password");
			   conn = DriverManager.getConnection(url, username, password);	
			   System.out.println("***** Connection to Database Successful *****");
		}catch(Exception e){
			throw e;
		}
		return conn;
		
	}

	public void close(){
		try {
			conn.close();
			System.out.println("Con Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
