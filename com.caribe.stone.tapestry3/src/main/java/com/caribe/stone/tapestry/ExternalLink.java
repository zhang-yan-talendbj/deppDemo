package com.caribe.stone.tapestry;

import org.apache.tapestry.IExternalPage;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.html.BasePage;

public class ExternalLink extends BasePage implements IExternalPage{

	public void activateExternalPage(Object[] parameters, IRequestCycle cycle) {
		System.out.println("in ExternalLink:");
		for (int i = 0; i < parameters.length; i++) {
			System.out.println(parameters[i]);
		}
	}

}
