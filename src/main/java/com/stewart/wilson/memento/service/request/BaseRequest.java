package com.stewart.wilson.memento.service.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseRequest {

	@JsonProperty("Transaction UUID")
	private String transactionUUID;
	@JsonProperty("User ID")
	private String userID;
	
	public String getTransactionUUID() {
		return transactionUUID;
	}
	public void setTransactionUUID(String transactionUUID) {
		this.transactionUUID = transactionUUID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	

}
