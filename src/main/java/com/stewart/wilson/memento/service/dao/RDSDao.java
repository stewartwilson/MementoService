package com.stewart.wilson.memento.service.dao;

import java.util.List;

import com.stewart.wilson.memento.service.pin.Pin;

public interface RDSDao {
	
	public List<Pin> queryPinsByUser(String user);

	public void addPin(Pin pin);
	
	public void updatePin(Pin pin);
	
	public void deletePin(Pin pin);
	
	public List<Pin> queryPinsByRange(int range, double userLatitude, double userLongitude);
	
}
