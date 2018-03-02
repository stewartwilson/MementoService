package com.stewart.wilson.memento.service.response;

import java.util.List;

import com.stewart.wilson.memento.service.pin.Pin;

public class GetAllPinsWithinRangeResponse extends BaseResponse {
	
	private List<Pin> pins;

	public List<Pin> getPins() {
		return pins;
	}

	public void setPins(List<Pin> pins) {
		this.pins = pins;
	}
}
