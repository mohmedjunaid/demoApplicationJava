package com.example.demo.response;

import java.util.List;

import com.example.demo.entity.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
	private List<String> messages;
	private ResponseStatus status;
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
}
