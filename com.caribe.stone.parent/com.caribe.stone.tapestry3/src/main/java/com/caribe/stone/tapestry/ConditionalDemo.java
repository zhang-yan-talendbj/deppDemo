package com.caribe.stone.tapestry;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry.html.BasePage;

public abstract class ConditionalDemo extends BasePage {

	public String getFullName() {
		return "bruec";
	}

	public boolean isManager() {
		return true;
	}

	public List getStaffList() {
		return new ArrayList();

	}
}