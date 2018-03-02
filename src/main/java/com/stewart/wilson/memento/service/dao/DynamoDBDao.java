package com.stewart.wilson.memento.service.dao;

import com.stewart.wilson.memento.service.request.BaseRequest;
import com.stewart.wilson.memento.service.response.BaseResponse;

public interface DynamoDBDao {
	public void storeRequestData(BaseRequest request);
	
	public void storeResponseData(BaseResponse request);
	
}
