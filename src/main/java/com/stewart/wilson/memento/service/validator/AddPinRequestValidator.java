package com.stewart.wilson.memento.service.validator;

import com.stewart.wilson.memento.service.pin.Pin;
import com.stewart.wilson.memento.service.request.AddPinRequest;
import com.stewart.wilson.memento.service.request.BaseRequest;

public class AddPinRequestValidator implements RequestValidator {

	@Override
	public void validateRequest(BaseRequest request) {
		validatePin(((AddPinRequest) request).getPin());
		
	}
	
	public void validatePin(Pin Pin) {
		//TODO validate all fields passed in Pin
	}
	

}
