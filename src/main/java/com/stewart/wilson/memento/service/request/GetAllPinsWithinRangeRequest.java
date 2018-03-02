package com.stewart.wilson.memento.service.request;

public class GetAllPinsWithinRangeRequest extends BaseRequest {

	private int range;
	private double latitude;
	private double longitude;

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
