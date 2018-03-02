package com.stewart.wilson.memento.service.request;

import com.stewart.wilson.memento.service.pin.Pin;

public class UpdatePinRequest extends BaseRequest {
	private Pin pin;

	public Pin getPin() {
		return pin;
	}

	public void setPin(Pin pin) {
		this.pin = pin;
	}
}
