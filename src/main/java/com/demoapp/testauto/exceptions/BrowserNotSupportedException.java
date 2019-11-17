package com.demoapp.testauto.exceptions;

public class BrowserNotSupportedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BrowserNotSupportedException(final String message) {
		super(message);
	}

	public BrowserNotSupportedException() {
		super("Please provide the browser name like chrome, ie or firefox");
	}

}
