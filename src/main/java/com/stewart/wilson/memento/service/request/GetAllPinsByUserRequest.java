package com.stewart.wilson.memento.service.request;

public class GetAllPinsByUserRequest extends BaseRequest {
	private String user;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
}
