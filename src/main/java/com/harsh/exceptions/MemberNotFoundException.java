package com.harsh.exceptions;

@SuppressWarnings("serial")
public class MemberNotFoundException extends RuntimeException{
	
	public MemberNotFoundException(String message) {
		super(message);
	}

}
