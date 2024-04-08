package com.km.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 *  Class kết nối với database.
 */
public class DBContext {
	
	/** The db name. */
	/*
	 * Use below method for your database connection for both single and multilpe
	 * sql server instance(s)
	 */
	private final String dbName = "lotterywebsite";
	
	/** The username. */
	private final String username = "root";
	
	/** The password. */
	private final String password = "12345";
	/** The host name. */
	/* Use below method for your database connection for both single and multilpe sql server instance(s) */
	private final String hostName="localhost:3306";

	/** The url. */
	private final String url="jdbc:mysql://"+hostName+"/"+dbName+"?characterEncoding=UTF-8";
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 * @throws Exception the exception
	 */
	public Connection getConnection() throws Exception{
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		Connection conn=null;
		
		try {
			conn=DriverManager.getConnection(url,username,password);
		} catch(SQLException e) {
			System.out.println("loi connected.");
			e.printStackTrace();
		}
		return conn;
		
	}


}
