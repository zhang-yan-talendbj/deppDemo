package com.caribe.stone.tapestry;

import java.util.ArrayList;
import java.util.LinkedList;
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
	
	public boolean isDisplayHistory(){
        List histories=new LinkedList();
        histories.add("");
        histories.add("");
        histories.add("");
        histories.add("");
        return histories==null?false:histories.size()>1;
    }
}