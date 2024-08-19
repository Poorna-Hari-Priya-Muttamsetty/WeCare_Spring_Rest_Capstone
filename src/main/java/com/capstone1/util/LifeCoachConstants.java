package com.capstone1.util;

public enum LifeCoachConstants {

	LIFECOACH_NOT_FOUND("lifecoach.not.found"),
	LIFECOACH_LOGIN_SUCCESS("login.success"),
	LIFECOACH_LOGIN_FAILED("login.failed");
	
	private String messge;

	private LifeCoachConstants(String messge) {
		this.messge = messge;
	}
	
    @Override
    public String toString() {
        return this.messge;
    }
}
