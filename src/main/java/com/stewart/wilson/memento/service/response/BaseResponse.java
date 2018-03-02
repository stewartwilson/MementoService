package com.stewart.wilson.memento.service.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
	@JsonProperty("Transaction UUID")
	private String transactionUUID;
	@JsonProperty("User ID")
	private String userID;
	@JsonProperty("Error Message")
	private String errorMessage;
	@JsonProperty("Success")
	private Boolean success = true;

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

	public Boolean isSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
