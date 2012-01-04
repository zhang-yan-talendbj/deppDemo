package com.caribe.stone.tapestry;

import org.apache.tapestry.IActionListener;
import org.apache.tapestry.IComponent;
import org.apache.tapestry.IRequestCycle;
import org.apache.tapestry.html.BasePage;

public class Home extends BasePage implements IActionListener {
	private String userName = "rockflyrain@gmail.com ";

	public String getUserName() {
		return this.userName;
	}

	public void customerSelectAction(IRequestCycle cycle) {
		System.out.println("hi,bruce.");
	}

	public void actionTriggered(IComponent component, IRequestCycle cycle) {
		// TODO Auto-generated method stub
		
	}
}
