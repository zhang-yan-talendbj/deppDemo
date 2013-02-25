package com.caribe.stone.tapestry;

import org.apache.tapestry.engine.RequestCycle;
import org.apache.tapestry.html.BasePage;

public abstract class EnquiryPage extends BasePage {
	public boolean isContactRep() {
		return false;
	}

	public void setContactRep(boolean value) {
		System.out.println(value);
	}

	public abstract boolean isStaff();

	public abstract void setStaff(boolean staff);

	public void formSubmit(RequestCycle cycle) {
		if (isContactRep()) {
			// Process contact a sales representative request
			System.out.println("1");
		}
	}
}