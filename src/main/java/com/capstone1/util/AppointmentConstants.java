package com.capstone1.util;

public enum AppointmentConstants {

	APPPOINTMENT_NOT_FOUND("appointment.not.found");
	
	private String message;

	private AppointmentConstants(String message) {
		this.message = message;
	}
	
    @Override
    public String toString() {
        return this.message;
    }
}
