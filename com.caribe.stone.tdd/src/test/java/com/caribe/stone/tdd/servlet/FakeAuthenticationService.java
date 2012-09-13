package com.caribe.stone.tdd.servlet;

import java.util.HashMap;
import java.util.Map;

public class FakeAuthenticationService implements AuthenticationSerivce {
	private Map<String, String> users = new HashMap<String, String>();

	@Override
	public boolean isValidLogin(String user, String pass) {
		return users.containsKey(user) && users.containsValue(pass);
	}

	public void addUser(String user, String pass) {
		users.put(user, pass);
	}
}
