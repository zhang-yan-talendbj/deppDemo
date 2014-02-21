package com.caribe.stone.anki;

public class ParseException extends Exception {

	private String msg;

	public ParseException(String string) {
		this.msg = string;
	}

}
