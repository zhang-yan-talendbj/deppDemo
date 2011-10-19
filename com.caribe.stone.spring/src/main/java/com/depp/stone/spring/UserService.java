package com.depp.stone.spring;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {
	boolean hasMatchUser(String userName, String password);
}