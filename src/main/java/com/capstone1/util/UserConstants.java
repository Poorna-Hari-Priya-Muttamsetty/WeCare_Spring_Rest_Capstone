package com.capstone1.util;

public enum UserConstants {

	USER_NOT_FOUND("user.not.found"),
	USER_LOGIN_SUCCESS("login.success"),
	USER_LOGIN_FAILED("login.failed");
	
	private String message;

	private UserConstants(String message) {
		this.message = message;
	}
	
    @Override
    public String toString() {
        return this.message;
    }
}
