package com.corejsf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

// or import javax.enterprise.context.SessionScoped;

@ManagedBean(name = "user")
// or @Named("user")
@SessionScoped
public class UserBean implements Serializable {
	private String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String newValue) {
		name = newValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String newValue) {
		password = newValue;
	}
}
