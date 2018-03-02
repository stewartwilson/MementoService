package com.stewart.wilson.memento.service.validator;

import com.stewart.wilson.memento.service.exception.RequestValidationException;
import com.stewart.wilson.memento.service.request.BaseRequest;

public interface RequestValidator {
	
	public void validateRequest(BaseRequest request) throws RequestValidationException;


}
