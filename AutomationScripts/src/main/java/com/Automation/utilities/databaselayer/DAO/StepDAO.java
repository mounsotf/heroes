package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.StepDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class StepDAO {

	// Suite getSuite();
	// Set<Suite> getAllSuites();
	// Suite getSuiteByName();
	// boolean insertSuite();
	// boolean updateSuite();
	// boolean deleteSuite();
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);

	public StepDAO() {

	}

	public StepDTO getStepById(int id) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM step where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				StepDTO step = extractStepFromResultSet(rs);
				// close connection
				DatabaseConnection.closeConnection();
				return step;
			}
		} catch (SQLException ex) {
			// ex.printStackTrace();
			logger.error("error when retriving data from step table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	public StepDTO getStepByCode(String code) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection()
					.prepareStatement("SELECT * FROM step where code=?");
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				StepDTO step = extractStepFromResultSet(rs);
				// close connection
				DatabaseConnection.closeConnection();
				return step;
			}
		} catch (SQLException ex) {
			logger.error("error when retriving data from step table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	public StepDTO getStepByName(String name) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM step where name=?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				StepDTO step = extractStepFromResultSet(rs);
				// close connection
				DatabaseConnection.closeConnection();
				return step;
			}

		} catch (SQLException ex) {
			logger.error("error when retriving data from step table", ex.getCause());
			DatabaseConnection.closeConnection();
		}
		return null;

	}

	public Set<StepDTO> getAllSteps() throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM step");
			ResultSet rs = ps.executeQuery();
			Set<StepDTO> steps = new HashSet<StepDTO>();
			while (rs.next()) {
				StepDTO step = extractStepFromResultSet(rs);
				steps.add(step);
			}
			// close connection
			DatabaseConnection.closeConnection();
			return steps;
		} catch (SQLException ex) {
			logger.error("error when retriving data from testcase table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	public HashMap<String, StepDTO> getStepsByTestCaseId(int testCaseId) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection()
					.prepareStatement("SELECT * FROM step where testcaseid=?");
			ps.setInt(1, testCaseId);
			ResultSet rs = ps.executeQuery();
			HashMap<String, StepDTO> hamsteps = new HashMap<String, StepDTO>();
			while (rs.next()) {
				StepDTO step = extractStepFromResultSet(rs);
				hamsteps.put(step.getName(), step);
			}
			// close connection
			DatabaseConnection.closeConnection();
			return hamsteps;
		} catch (SQLException ex) {
			logger.error("error when retriving data from step table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	//// private methods
	private StepDTO extractStepFromResultSet(ResultSet rs) throws SQLException {
		StepDTO step = new StepDTO();
		step.setId(rs.getInt("id"));
		step.setTestCaseId(rs.getInt("testcaseid"));
		step.setCode(rs.getString("code"));
		step.setName(rs.getString("name"));

		return step;
	}

}
