package com.stewart.wilson.memento.service.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.stewart.wilson.memento.service.pin.Pin;

public class MementoUtilities {

	/**
	 * 
	 * @returns Distance in meters
	 */
	public static double distanceBetweenPointsKilometers(double latitude1, double latitude2, double longitude1,
			double longitude2) {

		final int radius = 6370986; // Radius of the earth in meters

		double latitudeDistance = Math.toRadians(latitude2 - latitude1);
		double longitudeDistance = Math.toRadians(longitude2 - longitude1);
		double a = Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2)
				+ Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2))
						* Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = radius * c;

		distance = Math.pow(distance, 2);

		return Math.sqrt(distance);
	}

	/**
	 * 
	 * @returns Distance in Miles
	 */
	public static double distanceBetweenPointsMiles(double latitude1, double latitude2, double longitude1,
			double longitude2) {
		final double kmToMile = 0.621371; // miles in kilometer
		return distanceBetweenPointsKilometers(latitude1, latitude2, longitude1, longitude2) * kmToMile / 1000;
	}

	public static PreparedStatement generatePreparentInsertPin(Connection connection, Pin pin) throws SQLException {
		String query = "INSERT INTO allpins (user, city, state, country, latitude, longitude, image_location, description, rating, tags)"
		        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pin.getUser());
		statement.setString(2, pin.getCity());
		statement.setString(3, pin.getState());
		statement.setString(4, pin.getCountry());
		statement.setDouble(5, pin.getLatitude());
		statement.setDouble(6, pin.getLongitude());
		statement.setString(7, pin.getImageLocation());
		statement.setString(8, pin.getDescription());
		statement.setInt(9, pin.getRating());
		statement.setString(10, pin.getTags());

		
		return statement;
	}
	
	public static PreparedStatement generatePreparentUpdatePin(Connection connection, Pin pin) throws SQLException {
		String query = "UPDATE allpins SET city = ?, state = ?, country = ?, latitude = ?, longitude = ?, image_location = ?, description = ?, last_update_date = NOW(), tags = ? "
		        + " WHERE pin_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, pin.getCity());
		statement.setString(2, pin.getState());
		statement.setString(3, pin.getCountry());
		statement.setDouble(4, pin.getLatitude());
		statement.setDouble(5, pin.getLongitude());
		statement.setString(6, pin.getImageLocation());
		statement.setString(7, pin.getDescription());
		statement.setString(8, pin.getTags());
		statement.setInt(9, pin.getPinId());

		
		return statement;
	}
	
	public static PreparedStatement generatePreparentDeletePin(Connection connection, Pin pin) throws SQLException {
		String query = "DELETE FROM allpins "
		        + " WHERE pin_id = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, pin.getPinId());

		
		return statement;
	}
}
