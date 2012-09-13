package com.caribe.stone.tdd.servlet;

public interface AuthenticationSerivce {

	public abstract boolean isValidLogin(String user, String pass);

}