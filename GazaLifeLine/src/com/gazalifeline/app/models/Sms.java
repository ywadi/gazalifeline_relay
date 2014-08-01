package com.gazalifeline.app.models;

public class Sms {

	private int id;
	private String text;
	private String number;
	
	public Sms() {
		id = -1;
		text = null;
		number = null;
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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "[" + text + " | " + number + "]";
	}
}
