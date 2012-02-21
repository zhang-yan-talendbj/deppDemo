package com.caribe.stone.word;

import java.sql.Statement;

public interface StatementCallback {

	Object execute(Statement stmt);
}
