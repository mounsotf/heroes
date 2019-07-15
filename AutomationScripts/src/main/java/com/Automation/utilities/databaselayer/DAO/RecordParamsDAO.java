package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.RecordParamsDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class RecordParamsDAO {
	
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public RecordParamsDAO(){
    	
    }
    public RecordParamsDTO getRecordParamById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM recordparams where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	RecordParamsDTO recordparam = extractRecordParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return recordparam;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from stepinput table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}

public RecordParamsDTO getStepByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM recordparams where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	RecordParamsDTO recordparam = extractRecordParamsFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return recordparam;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from step table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    
public Set<RecordParamsDTO> getAllRecordParams() throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM recordparams");
            ResultSet rs = ps.executeQuery();
            Set <RecordParamsDTO> stepinputs = new HashSet<RecordParamsDTO>();
            while(rs.next())
            {
            	RecordParamsDTO stepinput = extractRecordParamsFromResultSet(rs);
            	stepinputs.add(stepinput);
            }
            // close connection
            DatabaseConnection.closeConnection();
            return stepinputs;
        } catch (SQLException ex) {
        	logger.error("error when retriving data from recordparams table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}  
public HashMap<String,RecordParamsDTO> getRecordParamsByRecordId(int RecordId) throws SQLException {
    
    try {
        // open connection if it is not opened
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM recordparams where idrecord=?");
        ps.setInt(1, RecordId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,RecordParamsDTO> hamrecordparams = new HashMap<String,RecordParamsDTO>();
        while(rs.next())
        {
        	RecordParamsDTO param = extractRecordParamsFromResultSet(rs);
        	hamrecordparams.put(param.getName(), param);
        }
        // close connection
        DatabaseConnection.closeConnection();
        return hamrecordparams;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from recordparams table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
}  
    //// private methods
    private RecordParamsDTO extractRecordParamsFromResultSet(ResultSet rs) throws SQLException {
    	RecordParamsDTO recordparam = new RecordParamsDTO();
    	recordparam.setId( rs.getInt("id") );
    	recordparam.setRecordId(rs.getInt("idrecord"));
    	recordparam.setName( rs.getString("name") );
    	recordparam.setValue( rs.getString("value") );
    	
        return recordparam;
    }

}

