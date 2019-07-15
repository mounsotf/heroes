package com.Automation.utilities.databaselayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.ConfigurationProperties;

public class DatabaseConnection {

	 public static final String FILE_PATH_PROPERTIES = ConfigurationProperties.INSTANCE.getLog4jFileName();
	 protected static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
	
	 private static Connection con = null;
	   protected DatabaseConnection() {
	      // Exists only to defeat instantiation.
	   }
	   public static Connection getConnection() {
		   String dbdriver = ConfigurationProperties.INSTANCE.getMysqlDriver(); 
		    String url = ConfigurationProperties.INSTANCE.getMysqlUrl();
			String UserName= ConfigurationProperties.INSTANCE.getMysqlUserName();
			String Password = ConfigurationProperties.INSTANCE.getMysqlUserPassword();
			
			try {
				if ((con == null) || (con.isClosed())) {
				    try {
						Class.forName(dbdriver) ;
						con = DriverManager.getConnection(url, UserName, Password);
					} catch (ClassNotFoundException | SQLException e) {
						logger.error("Error connecting to the database: " + e.getMessage());
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		    
		  return con;
	   }
	   
	   // close the connection
	    public static void closeConnection() {
	        try {
	            
	            if ((con != null) && (!con.isClosed())) {
	                con.close();
	                con=null;
	            }
	        } catch (Exception e) {

	        }
	    }

	
}
