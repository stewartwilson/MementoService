package com.stewart.wilson.memento.service.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stewart.wilson.memento.service.pin.Pin;
import com.stewart.wilson.memento.service.utility.MementoUtilities;

public class RDSDaoImpl implements RDSDao {

	private static final Logger logger = LoggerFactory.getLogger(RDSDaoImpl.class);

	private static Connection getRemoteConnection() {
		if (System.getProperty("RDS_HOSTNAME") != null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String dbName = System.getProperty("RDS_DB_NAME");
				String userName = System.getProperty("RDS_USERNAME");
				String password = System.getProperty("RDS_PASSWORD");
				String hostname = System.getProperty("RDS_HOSTNAME");
				String port = System.getProperty("RDS_PORT");
				String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName;
				logger.trace("Getting remote connection with connection string from environment variables.");
				Connection conn = DriverManager.getConnection(jdbcUrl, userName, password);
				conn.setAutoCommit(false);
				logger.info("Remote connection successful.");
				return conn;
			} catch (ClassNotFoundException e) {
				logger.warn(e.toString());
				System.err.println(e.toString());
			} catch (SQLException e) {
				logger.warn(e.toString());
				System.err.println(e.toString());
			}
		}
		return null;
	}

	public List<Pin> queryPinsByUser(String searchUser) {
		Connection conn = getRemoteConnection();
		Statement statement = null;
		List<Pin> pins = new ArrayList<Pin>();
		try {

			System.out.println("Creating statement...");
			statement = conn.createStatement();
			String sql;
			sql = "SELECT * FROM allpins WHERE user = '" + searchUser + "'";
			ResultSet rs = statement.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				Pin pin = new Pin();
				// Retrieve by column name
				pin.setPinId(rs.getInt("pin_id"));
				pin.setUser(rs.getString("user"));
				pin.setCity(rs.getString("city"));
				pin.setCountry(rs.getString("country"));
				pin.setCreationDate(rs.getDate("creation_date"));
				pin.setLastUpdateDate(rs.getDate("last_update_date"));
				pin.setImageLocation(rs.getString("image_location"));
				pin.setRating(rs.getInt("rating"));
				pin.setState(rs.getString("state"));
				pin.setTags(rs.getString("tags"));
				pin.setIsVideo(rs.getBoolean("is_video"));
				pin.setLatitude(rs.getDouble("latitude"));
				pin.setLongitude(rs.getDouble("longitude"));
				pin.setViewableRange(rs.getInt("viewable_range"));

				pins.add(pin);
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return pins;

	}

	/**
	 * Range is currently working in kilometers only
	 */
	public List<Pin> queryPinsByRange(int range, double userLatitude, double userLongitude) {
		Connection conn = getRemoteConnection();
		List<Pin> pins = new ArrayList<Pin>();
		CallableStatement statement = null;
		try {

			System.out.println("Creating statement...");
			String query = "{ call getPinsWithinRange(?, ?, ?) }";
			statement = conn.prepareCall(query);
			statement.setDouble("userLat", userLatitude);
			statement.setDouble("userLong", userLongitude);
			statement.setInt("rangeLength", range);

			ResultSet rs = statement.executeQuery();

			// STEP 5: Extract data from result set
			while (rs.next()) {
				Pin pin = new Pin();
				// Retrieve by column name
				pin.setPinId(rs.getInt("pin_id"));
				pin.setUser(rs.getString("user"));
				pin.setCity(rs.getString("city"));
				pin.setCountry(rs.getString("country"));
				pin.setCreationDate(rs.getDate("creation_date"));
				pin.setLastUpdateDate(rs.getDate("last_update_date"));
				pin.setImageLocation(rs.getString("image_location"));
				pin.setRating(rs.getInt("rating"));
				pin.setState(rs.getString("state"));
				pin.setTags(rs.getString("tags"));
				pin.setIsVideo(rs.getBoolean("is_video"));
				pin.setLatitude(rs.getDouble("latitude"));
				pin.setLongitude(rs.getDouble("longitude"));
				pin.setViewableRange(rs.getInt("viewable_range"));

				pins.add(pin);
			}
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return pins;

	}

	public void addPin(Pin pin) {
		Connection conn = getRemoteConnection();
		Statement statement = null;
		try {
			/*
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String json = mapper.writeValueAsString(pin);
			*/
			

			// create the prepared statement for insert
			PreparedStatement preparedStatement = MementoUtilities.generatePreparentInsertPin(conn, pin);

			preparedStatement.execute();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

	public void updatePin(Pin pin) {
		Connection conn = getRemoteConnection();
		Statement statement = null;
		try {

			// create the prepared statement for insert
			PreparedStatement preparedStatement = MementoUtilities.generatePreparentUpdatePin(conn, pin);

			preparedStatement.execute();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public void deletePin(Pin pin) {
		Connection conn = getRemoteConnection();
		Statement statement = null;
		try {
			// create the prepared statement for insert
			PreparedStatement preparedStatement = MementoUtilities.generatePreparentDeletePin(conn, pin);

			preparedStatement.execute();
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close resources
			try {
				if (statement != null)
					statement.close();
			} catch (SQLException e) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}

}
