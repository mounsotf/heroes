package com.Automation.utilities.databaselayer.DAO;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.PageDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class PageDAO {
	
	//Suite getSuite();
  //  Set<Suite> getAllSuites();
    //Suite getSuiteByName();
   // boolean insertSuite();
   // boolean updateSuite();
   // boolean deleteSuite();
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public PageDAO(){
    	
    }
    public PageDTO getPageById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM page where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	PageDTO page = extractPageFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return page;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from page table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}

public PageDTO getPageByName(long appid , String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM page where appid = ? and name=?");
            ps.setLong(1, appid);
            ps.setString(2, name);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	PageDTO page = extractPageFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return page;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from page table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    
public Set<PageDTO> getAllPages() throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM page");
            ResultSet rs = ps.executeQuery();
            Set <PageDTO> pages = new HashSet<PageDTO>();
            while(rs.next())
            {
            	PageDTO page = extractPageFromResultSet(rs);
            	pages.add(page);
            }
            // close connection
            DatabaseConnection.closeConnection();
            return pages;
        } catch (SQLException ex) {
        	logger.error("error when retriving data from page table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}  
public HashMap<String,PageDTO> getPagesByApplicationId(int applicationId) throws SQLException {
    
    try {
        // open connection if it is not opened
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM page where appid=?");
        ps.setInt(1, applicationId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,PageDTO> pages = new HashMap<String,PageDTO>();
        while(rs.next())
        {
        	PageDTO page = extractPageFromResultSet(rs);
        	pages.put(page.getId() + "-" +page.getName(), page);
        }
        // close connection
        DatabaseConnection.closeConnection();
        return pages;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from testcase table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
}  
    //// private methods
    private PageDTO extractPageFromResultSet(ResultSet rs) throws SQLException {
    	PageDTO testCase = new PageDTO();
    	testCase.setId( rs.getInt("id") );
    	testCase.setAppId( rs.getInt("appid") );
    	testCase.setName( rs.getString("name") );
    	
        return testCase;
    }

}

