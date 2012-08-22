package com.caribe.stone.wd.service;

import java.util.List;

import com.caribe.stone.wd.entities.User;


public interface UserService {

	public Long saveUser(User user);

	public boolean isInvaildUser(User user);

	public abstract List<User> findUser(String username, String password);
}
