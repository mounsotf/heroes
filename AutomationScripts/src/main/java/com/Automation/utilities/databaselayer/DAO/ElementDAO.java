package com.Automation.utilities.databaselayer.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Automation.utilities.databaselayer.DTO.ElementDTO;
import com.Automation.utilities.databaselayer.DatabaseConnection;

public class ElementDAO {

	// Suite getSuite();
	// Set<Suite> getAllSuites();
	// Suite getSuiteByName();
	// boolean insertSuite();
	// boolean updateSuite();
	// boolean deleteSuite();
	private static Logger logger = LogManager.getLogger(DatabaseConnection.class);

	public ElementDAO() {

	}

	public ElementDTO getElementById(int id) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement("SELECT * FROM element where id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ElementDTO element = extractElementFromResultSet(rs);
				// close connection
				DatabaseConnection.closeConnection();
				return element;
			}
		} catch (SQLException ex) {
			// ex.printStackTrace();
			logger.error("error when retriving data from element table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	public ElementDTO getStepPageIdAndName(long pageid, String name) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection()
					.prepareStatement("SELECT * FROM element where pageid=? and name =?");
			ps.setLong(1, pageid);
			ps.setString(2, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				ElementDTO step = extractElementFromResultSet(rs);
				// close connection
				DatabaseConnection.closeConnection();
				return step;
			}
		} catch (SQLException ex) {
			logger.error("error when retriving data from Element table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	
	

	public HashMap<String, ElementDTO> getElementsByPageId(int pageId) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection()
					.prepareStatement("SELECT * FROM element where pageid=?");
			ps.setInt(1, pageId);
			ResultSet rs = ps.executeQuery();
			HashMap<String, ElementDTO> hamelements = new HashMap<String, ElementDTO>();
			while (rs.next()) {
				ElementDTO element = extractElementFromResultSet(rs);
				hamelements.put(element.getName(), element);
			}
			// close connection
			DatabaseConnection.closeConnection();
			return hamelements;
		} catch (SQLException ex) {
			logger.error("error when retriving data from Element table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}
	public HashMap<String, String> getElementsLocationByPageId(int pageId) throws SQLException {

		try {
			// open connection if it is not opened
			PreparedStatement ps = DatabaseConnection.getConnection()
					.prepareStatement("SELECT * FROM element where pageid=?");
			ps.setInt(1, pageId);
			ResultSet rs = ps.executeQuery();
			HashMap<String, String> hamelements = new HashMap<String, String>();
			while (rs.next()) {
				ElementDTO element = extractElementFromResultSet(rs);
				hamelements.put(element.getName(), element.getLocation());
			}
			// close connection
			DatabaseConnection.closeConnection();
			return hamelements;
		} catch (SQLException ex) {
			logger.error("error when retriving data from Element table", ex.getCause());
			DatabaseConnection.closeConnection();
		}

		return null;
	}

	//// private methods
	private ElementDTO extractElementFromResultSet(ResultSet rs) throws SQLException {
		ElementDTO step = new ElementDTO();
		step.setId(rs.getInt("id"));
		step.setPageId(rs.getInt("pageid"));
		step.setName(rs.getString("name"));
		step.setLocation(rs.getString("location"));

		return step;
	}

}
