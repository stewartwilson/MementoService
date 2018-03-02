package com.stewart.wilson.memento.service.dao;

import java.util.HashMap;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stewart.wilson.memento.service.request.BaseRequest;
import com.stewart.wilson.memento.service.response.BaseResponse;

public class DynamoDBDaoImpl implements DynamoDBDao {

	@Override
	public void storeRequestData(BaseRequest request) {
		AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String json = mapper.writeValueAsString(request);
			HashMap<String,AttributeValue> itemKeys =
					   new HashMap<String,AttributeValue>();
			itemKeys.put("Transaction UUID", new AttributeValue(request.getTransactionUUID()));
			itemKeys.put("User ID", new AttributeValue(request.getTransactionUUID()));
			itemKeys.put("Request Type", new AttributeValue(request.getClass().getName()));
			itemKeys.put("Request", new AttributeValue(json));
			System.out.println("Putting item for " + request.getTransactionUUID());
			dynamoDB.putItem("TransactionHistory", itemKeys);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void storeResponseData(BaseResponse reponse) {
		AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
			mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
			String json = mapper.writeValueAsString(reponse);
			HashMap<String,AttributeValue> itemKeys =
					   new HashMap<String,AttributeValue>();
			itemKeys.put("Transaction UUID", new AttributeValue(reponse.getTransactionUUID()));
			itemKeys.put("User ID", new AttributeValue(reponse.getTransactionUUID()));
			itemKeys.put("Response Type", new AttributeValue(reponse.getClass().getName()));
			itemKeys.put("Response Message", new AttributeValue(reponse.getErrorMessage()));
			System.out.println("Putting item for " + reponse.getTransactionUUID());
			dynamoDB.putItem("TransactionHistory", itemKeys);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
