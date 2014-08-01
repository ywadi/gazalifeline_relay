package com.gazalifeline.app.models;

public class Sms {

	private int id;
	private String text;
	private String relayNumber;
	
	public Sms() {
		id = -1;
		text = null;
		relayNumber = null;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRelayNumber() {
		return relayNumber;
	}

	public void setRelayNumber(String relayNumber) {
		this.relayNumber = relayNumber;
	}

	@Override
	public String toString() {
		return "[" + text + " | " + relayNumber + "]";
	}
}
