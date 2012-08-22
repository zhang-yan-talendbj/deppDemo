package com.caribe.stone.tapestry;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tapestry.html.BasePage;

public abstract class InsertDemo extends BasePage {
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd MMM yyyy");

	public Date getDueDate() {
		return new Date();
	}

	public abstract String getDueClass();
}
