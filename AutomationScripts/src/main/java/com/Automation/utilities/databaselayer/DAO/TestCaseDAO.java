package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.TestCaseDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class TestCaseDAO {
	
	//Suite getSuite();
  //  Set<Suite> getAllSuites();
    //Suite getSuiteByName();
   // boolean insertSuite();
   // boolean updateSuite();
   // boolean deleteSuite();
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public TestCaseDAO(){
    	
    }
    public TestCaseDTO getTestCaseById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcase where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	TestCaseDTO suite = extractTestCaseFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return suite;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from testcase table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
public TestCaseDTO getTestCaseByCode(String code) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcase where code=?");
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	TestCaseDTO suite = extractTestCaseFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return suite;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from testcase table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
public TestCaseDTO getTestCaseByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcase where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	TestCaseDTO suite = extractTestCaseFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return suite;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from testcase table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    
public Set<TestCaseDTO> getAllTestCases() throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcase");
            ResultSet rs = ps.executeQuery();
            Set <TestCaseDTO> suites = new HashSet<TestCaseDTO>();
            while(rs.next())
            {
            	TestCaseDTO suite = extractTestCaseFromResultSet(rs);
            	suites.add(suite);
            }
            // close connection
            DatabaseConnection.closeConnection();
            return suites;
        } catch (SQLException ex) {
        	logger.error("error when retriving data from testcase table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}  
public HashMap<String,TestCaseDTO> getTestCasesByApplicationId(int applicationId) throws SQLException {
    
    try {
        // open connection if it is not opened
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM testcase where appid=?");
        ps.setInt(1, applicationId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,TestCaseDTO> testcases = new HashMap<String,TestCaseDTO>();
        while(rs.next())
        {
        	TestCaseDTO testcase = extractTestCaseFromResultSet(rs);
        	testcases.put(testcase.getName(), testcase);
        }
        // close connection
        DatabaseConnection.closeConnection();
        return testcases;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from testcase table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
}  
    //// private methods
    private TestCaseDTO extractTestCaseFromResultSet(ResultSet rs) throws SQLException {
    	TestCaseDTO testCase = new TestCaseDTO();
    	testCase.setId( rs.getInt("id") );
    	testCase.setAppId( rs.getInt("appid") );
    	testCase.setCode( rs.getString("code") );
    	testCase.setName( rs.getString("name") );
    	
        return testCase;
    }

}

