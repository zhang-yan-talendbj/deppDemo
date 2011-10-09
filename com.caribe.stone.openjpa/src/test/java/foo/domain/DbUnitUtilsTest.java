package foo.domain;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.ITable;
import org.dbunit.ext.h2.H2Connection;
import org.junit.Test;

public class DbUnitUtilsTest {


	@Test
	public void testAppendData() throws DatabaseUnitException,
			ClassNotFoundException, SQLException {

		Class.forName("org.h2.Driver");
		Connection h2Connection = DriverManager.getConnection("jdbc:h2:tcp://10.69.4.187:9092/test", "sa", "");
		System.out.println(h2Connection);
		IDatabaseConnection connection = new H2Connection(h2Connection, "test");
		System.out.println(connection);
		ITable person = connection.createTable("person");
	}

}
