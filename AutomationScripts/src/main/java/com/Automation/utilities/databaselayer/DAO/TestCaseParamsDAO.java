package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.BaseDTO;
import com.Automation.utilities.databaselayer.DTO.TestCaseParamsDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class TestCaseParamsDAO {
	
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public TestCaseParamsDAO(){
    	
    }
    public TestCaseParamsDTO getTestCaseParamById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcaseparams where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	TestCaseParamsDTO testcaseparam = extractTestCaseParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return testcaseparam;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from testcaseparams table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}

public TestCaseParamsDTO getTestCaseByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcaseparams where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	TestCaseParamsDTO testcaseparam = extractTestCaseParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return testcaseparam;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from testcaseparams table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    

public HashMap<String, BaseDTO> getTestCaseParamsByTestCaseId(int testCaseId) throws SQLException {
    
    try {
        // open connection if it is not opened
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcaseparams where testcaseid=?");
        ps.setInt(1, testCaseId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,BaseDTO> hamrecordparams = new HashMap<String,BaseDTO>();
        while(rs.next())
        {
        	TestCaseParamsDTO param = extractTestCaseParamsFromResultSet(rs);
        	hamrecordparams.put(param.getName(), param);
        }
        // close connection
        DatabaseConnection.closeConnection();
        return hamrecordparams;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from testcaseparams table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
}  
    //// private methods
    private TestCaseParamsDTO extractTestCaseParamsFromResultSet(ResultSet rs) throws SQLException {
    	TestCaseParamsDTO testcaseparam = new TestCaseParamsDTO();
    	testcaseparam.setId( rs.getInt("id") );
    	testcaseparam.setTestCaseId(rs.getInt("testcaseid"));
    	testcaseparam.setName( rs.getString("name") );
    	testcaseparam.setValue( rs.getString("value") );
    	
        return testcaseparam;
    }

}

