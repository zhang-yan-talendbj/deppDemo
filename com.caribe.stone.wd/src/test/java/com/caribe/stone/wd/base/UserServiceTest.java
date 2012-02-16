package com.caribe.stone.wd.base;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.caribe.stone.wd.entities.User;
import com.caribe.stone.wd.service.UserService;
import com.caribe.stone.wd.service.impl.UserServiceJDBCImpl;

public class UserServiceTest extends SpringTest {

	@Autowired
	private UserService userServiceJDBCImpl;

	public UserService getUserServiceJDBCImpl() {
		return userServiceJDBCImpl;
	}

	public void setUserServiceJDBCImpl(UserService userServiceImpl) {
		this.userServiceJDBCImpl = userServiceImpl;
	}

	@Test
	public void saveUser() throws Exception {
		User user = new User();
		user.setUsername("thinkdeeply");
		user.setPassword(" 2012-02-16");
		Long userId = userServiceJDBCImpl.saveUser(user);
		assertNotNull(userId);
	}
	
	@Test
	public void findUser() throws Exception {
		List<User> user = userServiceJDBCImpl.findUser("thinkdeeply", " 2012-02-16");
		for (User u : user) {
			System.out.println(u);
		}
	}
	
}
