package com.stewart.wilson.memento.service;

import java.util.List;

import com.stewart.wilson.memento.service.dao.DynamoDBDao;
import com.stewart.wilson.memento.service.dao.RDSDao;
import com.stewart.wilson.memento.service.exception.RequestValidationException;
import com.stewart.wilson.memento.service.pin.Pin;
import com.stewart.wilson.memento.service.request.AddPinRequest;
import com.stewart.wilson.memento.service.request.CommentOnPinRequest;
import com.stewart.wilson.memento.service.request.DeletePinRequest;
import com.stewart.wilson.memento.service.request.FollowUserRequest;
import com.stewart.wilson.memento.service.request.GetAllPinsByUserRequest;
import com.stewart.wilson.memento.service.request.GetAllPinsWithinRangeRequest;
import com.stewart.wilson.memento.service.request.LikePinRequest;
import com.stewart.wilson.memento.service.request.UpdatePinRequest;
import com.stewart.wilson.memento.service.response.AddPinResponse;
import com.stewart.wilson.memento.service.response.CommentOnPinResponse;
import com.stewart.wilson.memento.service.response.DeletePinResponse;
import com.stewart.wilson.memento.service.response.FollowUserResponse;
import com.stewart.wilson.memento.service.response.GetAllPinsByUserResponse;
import com.stewart.wilson.memento.service.response.GetAllPinsWithinRangeResponse;
import com.stewart.wilson.memento.service.response.LikePinResponse;
import com.stewart.wilson.memento.service.response.UpdatePinResponse;
import com.stewart.wilson.memento.service.validator.AddPinRequestValidator;
import com.stewart.wilson.memento.service.validator.DeletePinRequestValidator;
import com.stewart.wilson.memento.service.validator.GetAllPinsByUserRequestValidator;
import com.stewart.wilson.memento.service.validator.RequestValidator;
import com.stewart.wilson.memento.service.validator.UpdatePinRequestValidator;

public class MementoServiceImpl implements MementoService {
	
	private DynamoDBDao dynamoDBDao;
	private RDSDao rdsDao;

	public DynamoDBDao getDynamoDBDao() {
		return dynamoDBDao;
	}

	public void setDynamoDBDao(DynamoDBDao dynamoDBDao) {
		this.dynamoDBDao = dynamoDBDao;
	}

	public RDSDao getRDSDao() {
		return rdsDao;
	}

	public void setRDSDao(RDSDao rdsDao) {
		this.rdsDao = rdsDao;
	}

	public AddPinResponse addPin(AddPinRequest request) {
		AddPinResponse response = new AddPinResponse();
		RequestValidator validator = new AddPinRequestValidator();
		try
		{
			//dynamoDBDao.storeRequestData(request);
			response.setTransactionUUID(request.getTransactionUUID());
			response.setUserID(request.getUserID());
			validator.validateRequest(request);
			rdsDao.addPin(request.getPin());
			response.setSuccess(true);
		}
		catch (RequestValidationException e)
		{
			response.setSuccess(false);
			response.setErrorMessage("Request Validation Failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		} finally {
			//dynamoDBDao.storeResponseData(response);
		}

		return response;
	}
	
	public UpdatePinResponse updatePin(UpdatePinRequest request) {
		UpdatePinResponse response = new UpdatePinResponse();
		RequestValidator validator = new UpdatePinRequestValidator();
		try
		{
			//dynamoDBDao.storeRequestData(request);
			response.setTransactionUUID(request.getTransactionUUID());
			response.setUserID(request.getUserID());
			validator.validateRequest(request);
			rdsDao.updatePin(request.getPin());
			response.setSuccess(true);
		}
		catch (RequestValidationException e)
		{
			response.setSuccess(false);
			response.setErrorMessage("Request Validation Failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		} finally {
			//dynamoDBDao.storeResponseData(response);
		}

		return response;
	}
	
	public DeletePinResponse deletePin(DeletePinRequest request) {
		DeletePinResponse response = new DeletePinResponse();
		RequestValidator validator = new DeletePinRequestValidator();
		try
		{
			//dynamoDBDao.storeRequestData(request);
			response.setTransactionUUID(request.getTransactionUUID());
			response.setUserID(request.getUserID());
			validator.validateRequest(request);
			rdsDao.deletePin(request.getPin());
			response.setSuccess(true);
		}
		catch (RequestValidationException e)
		{
			response.setSuccess(false);
			response.setErrorMessage("Request Validation Failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		} finally {
			//dynamoDBDao.storeResponseData(response);
		}

		return response;
	}

	public GetAllPinsByUserResponse getAllPinsByUser(GetAllPinsByUserRequest request) {
		GetAllPinsByUserResponse response = new GetAllPinsByUserResponse();
		RequestValidator validator = new GetAllPinsByUserRequestValidator();
		try
		{	
			dynamoDBDao.storeRequestData(request);
			response.setTransactionUUID(request.getTransactionUUID());
			response.setUserID(request.getUserID());
			validator.validateRequest(request);
			rdsDao.queryPinsByUser(request.getUser());
			response.setPins(rdsDao.queryPinsByUser(request.getUser()));
			response.setSuccess(true);
		}
		catch (RequestValidationException e)
		{
			response.setSuccess(false);
			response.setErrorMessage("Request Validation Failed: " + e.getMessage());
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		} finally {
			dynamoDBDao.storeResponseData(response);
		}

		return response;
	}
	
	@Override
	public GetAllPinsWithinRangeResponse getAllPinsWithinRange(GetAllPinsWithinRangeRequest request) {
		GetAllPinsWithinRangeResponse response = new GetAllPinsWithinRangeResponse();
		RequestValidator validator;
		try
		{	
			//dynamoDBDao.storeRequestData(request);
			response.setTransactionUUID("Response_"+request.getTransactionUUID());
			response.setUserID(request.getUserID());
			//validator.validateRequest(request);
			List<Pin> pins = rdsDao.queryPinsByRange(request.getRange(), request.getLatitude(), request.getLongitude());
			response.setPins(pins);
			response.setSuccess(true);
		}
		catch (Exception e)
		{
			response.setSuccess(false);
			response.setErrorMessage(e.getClass() + ": " + e.getMessage());
		} finally {
			//dynamoDBDao.storeResponseData(response);
		}

		return response;
	}

	@Override
	public LikePinResponse likePin(LikePinRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommentOnPinResponse commentOnPin(CommentOnPinRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FollowUserResponse followUser(FollowUserRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
	
}
