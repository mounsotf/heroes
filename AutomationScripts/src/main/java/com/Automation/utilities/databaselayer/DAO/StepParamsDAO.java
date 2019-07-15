package com.Automation.utilities.databaselayer.DAO;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.BaseDTO;
import com.Automation.utilities.databaselayer.DTO.StepParamsDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class StepParamsDAO {
	
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public StepParamsDAO(){
    	
    }
    public StepParamsDTO getsteparamsById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM steparams where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	StepParamsDTO stepParam = extractStepParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return 	stepParam;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from steparams table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}

public StepParamsDTO getsteparamsByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM steparams where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	StepParamsDTO stepparam = extractStepParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return stepparam;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from stepparams table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    

public HashMap<String,BaseDTO> getStepParamsByStepId(int stepId) throws SQLException {
    
    try {
        // open connection if it is not opened
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM steparams where stepid=?");
        ps.setInt(1, stepId);
        ResultSet rs = ps.executeQuery();
        HashMap<String, BaseDTO> hamrecordparams = new HashMap<String,BaseDTO>();
        while(rs.next())
        {
        	StepParamsDTO param = extractStepParamsFromResultSet(rs);
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
    private StepParamsDTO extractStepParamsFromResultSet(ResultSet rs) throws SQLException {
    	StepParamsDTO stepparam = new StepParamsDTO();
    	stepparam.setId( rs.getInt("id") );
    	stepparam.setTestCaseId(rs.getInt("stepid"));
    	stepparam.setName( rs.getString("name") );
    	stepparam.setValue( rs.getString("value") );
    	
        return stepparam;
    }

}

