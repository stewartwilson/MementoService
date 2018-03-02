package com.stewart.wilson.memento.service.validator;

import com.stewart.wilson.memento.service.pin.Pin;
import com.stewart.wilson.memento.service.request.BaseRequest;
import com.stewart.wilson.memento.service.request.UpdatePinRequest;

public class UpdatePinRequestValidator implements RequestValidator {

	@Override
	public void validateRequest(BaseRequest request) {
		validatePin(((UpdatePinRequest) request).getPin());
		
	}
	
	public void validatePin(Pin Pin) {
		//TODO validate all fields passed in Pin
	}
	

}
