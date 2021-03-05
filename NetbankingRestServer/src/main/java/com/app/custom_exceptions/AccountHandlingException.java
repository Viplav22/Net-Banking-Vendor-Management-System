package com.app.custom_exceptions;

@SuppressWarnings("serial")
public class AccountHandlingException extends RuntimeException {
public AccountHandlingException(String mesg) {
	super(mesg);
}
}
