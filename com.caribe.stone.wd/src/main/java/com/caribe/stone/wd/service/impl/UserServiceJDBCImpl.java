package com.caribe.stone.wd.service.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.stereotype.Component;

import com.caribe.stone.wd.entities.User;
import com.caribe.stone.wd.service.UserService;

@Component
public class UserServiceJDBCImpl implements UserService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Long saveUser(final User user) {

		Long userId = jdbcTemplate.execute(new StatementCallback<Long>() {
			@Override
			public Long doInStatement(Statement stmt) throws SQLException, DataAccessException {
				Long id = null;
				String sql = "insert into t_user(USERNAME,PASSWORD) values('" + user.getUsername()
						+ "','" + user.getPassword() + "') ";
				System.out.println("Log:RegisterServlet.doPost" + sql);
				stmt.execute(sql, Statement.RETURN_GENERATED_KEYS);
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if (generatedKeys.next()) {
					id = generatedKeys.getLong(1);
				} else {
				}
				return id;
			}
		});
		return userId;
	}

	@Override
	public boolean isInvaildUser(User user) {
		return false;
	}

	@Override
	public List<User> findUser(String username, String password) {
	 List<User> u = jdbcTemplate.query("select id,username,password from t_user where username='"+username+"' and password='"+password+"'", new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getLong(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				return user;
			}
		});
		return u;
	}
}
