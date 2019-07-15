package com.Automation.utilities.databaselayer.DAO;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.ApplicationDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class ApplicationDAO {
	
	//Suite getSuite();
  //  Set<Suite> getAllSuites();
    //Suite getSuiteByName();
   // boolean insertSuite();
   // boolean updateSuite();
   // boolean deleteSuite();
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public ApplicationDAO(){
    	
    }
    public ApplicationDTO getApplicationById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM application where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	ApplicationDTO app = extractApplicationFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return app;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from application table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
public ApplicationDTO getApplicationByCode(String code) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM application where code=?");
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	ApplicationDTO app = extractApplicationFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return app;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from application table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
public ApplicationDTO getApplicationByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM application where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	ApplicationDTO app = extractApplicationFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return app;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from application table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    
public Set<ApplicationDTO> getAllApplications() throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM application");
            ResultSet rs = ps.executeQuery();
            Set <ApplicationDTO> apps = new HashSet<ApplicationDTO>();
            while(rs.next())
            {
            	ApplicationDTO app = extractApplicationFromResultSet(rs);
            	apps.add(app);
            }
            // close connection
            DatabaseConnection.closeConnection();
            return apps;
        } catch (SQLException ex) {
        	logger.error("error when retriving data from application table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}  

    //// private methods
    private ApplicationDTO extractApplicationFromResultSet(ResultSet rs) throws SQLException {
    	ApplicationDTO app = new ApplicationDTO();
    	app.setId( rs.getInt("id") );
    	app.setCode( rs.getString("code") );
    	app.setName( rs.getString("name") );
    	
        return app;
    }

}

