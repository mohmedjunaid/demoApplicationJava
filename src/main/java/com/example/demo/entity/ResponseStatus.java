package com.example.demo.entity;

public enum ResponseStatus {
	SUCCESS("SUCCESS"), FAILURE("FAILURE");

	private String value;

	ResponseStatus(String status) {
		this.value = status;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
