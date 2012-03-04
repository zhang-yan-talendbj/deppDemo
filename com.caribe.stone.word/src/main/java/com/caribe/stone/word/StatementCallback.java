package com.caribe.stone.word;

import java.sql.SQLException;
import java.sql.Statement;

public interface StatementCallback {
	Object execute(Statement stmt) throws SQLException;
}
