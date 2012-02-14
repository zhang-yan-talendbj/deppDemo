package com.caribe.stone.wd;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementCallback {
	public Object execute(Statement stmt)throws SQLException;
}
