package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.RecordDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class RecordDAO {
	
	
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);
		
    public RecordDAO(){
    	
    }
   
    public RecordDTO getRecordById(int id) throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM record where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	RecordDTO record = extractRecordFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return record;
            }
        } catch (SQLException ex) {
            //ex.printStackTrace();
            logger.error("error when retriving data from record table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}

public RecordDTO getRecordByName(String name) throws SQLException {
        
        try {
            // open connection if it is not opened
        	PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM record where name=?");
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
            	RecordDTO record = extractRecordFromResultSet(rs);
                // close connection
            	DatabaseConnection.closeConnection();
                return record;
            }
        } catch (SQLException ex) {
        	logger.error("error when retriving data from record table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}
    
public Set<RecordDTO> getAllRecords() throws SQLException {
        
        try {
            // open connection if it is not opened
            PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM record order by recorder asc");
            ResultSet rs = ps.executeQuery();
            Set <RecordDTO> records = new HashSet<RecordDTO>();
            while(rs.next())
            {
            	RecordDTO record = extractRecordFromResultSet(rs);
            	records.add(record);
            }
            // close connection
            DatabaseConnection.closeConnection();
            return records;
        } catch (SQLException ex) {
        	logger.error("error when retriving data from record table", ex.getCause());
            DatabaseConnection.closeConnection();
        }
       
    return null;
}  
public HashMap<String,RecordDTO> getRecordsByStepId(int StepId) throws SQLException {
    
    try {
        // open connection if it is not opened
    	// get only the first root level records
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM record where stepid=? and parentid is null order by recorder asc");
        ps.setInt(1, StepId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,RecordDTO> hamrecords = new HashMap<String,RecordDTO>();
        while(rs.next())
        {
        	RecordDTO record = extractRecordFromResultSet(rs);
        	hamrecords.put(record.getId() + "-"+ record.getName(), record);
        }
        
       // order records by recorder
      
        HashMap<String, RecordDTO> sortedhamrecords = hamrecords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
              	
        // close connection
        DatabaseConnection.closeConnection();
        return sortedhamrecords;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from record table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
}  
public HashMap<String,RecordDTO> getRecordsByParentId(int parentId) throws SQLException {
    
    try {
        // open connection if it is not opened
    	// get the first level children records only
        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM record where parentid=? order by recorder asc");
        ps.setInt(1, parentId);
        ResultSet rs = ps.executeQuery();
        HashMap<String,RecordDTO> hamrecords = new HashMap<String,RecordDTO>();
        while(rs.next())
        {
        	RecordDTO record = extractRecordFromResultSet(rs);
        	hamrecords.put(record.getId() + "-"+ record.getName(), record);
        }
     // order records by recorder
        
        HashMap<String, RecordDTO> sortedhamrecords = hamrecords.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        // close connection
        DatabaseConnection.closeConnection();
        return sortedhamrecords;
    } catch (SQLException ex) {
    	logger.error("error when retriving data from record table", ex.getCause());
        DatabaseConnection.closeConnection();
    }
   
return null;
} 
    //// private methods
    private RecordDTO extractRecordFromResultSet(ResultSet rs) throws SQLException {
    	RecordDTO record = new RecordDTO();
    	record.setId( rs.getInt("id") );
    	record.setParentId(rs.getInt("parentid") );
    	record.setStepId( rs.getInt("stepid")) ;
    	record.setName( rs.getString("name") );
    	record.setRecOrder(rs.getInt("recorder"));
    	
        return record;
    }

}

