package foo.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.AmbiguousTableNameException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.ForwardOnlyResultSetTableFactory;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.db2.Db2Connection;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.operation.DatabaseOperation;

public class DBXMLHelper {
	private DBXMLHelper() {
	};

	public static void cleanInsertXmlData(Connection con, File file)
			throws DatabaseUnitException, FileNotFoundException, IOException,
			DataSetException, SQLException {
		IDataSet dataSet = new FlatXmlDataSet(file);
		Db2Connection cc = new Db2Connection(con, "website");
		cc.getConfig().setFeature(
				"http://www.dbunit.org/features/qualifiedTableNames", true);
		DatabaseOperation.CLEAN_INSERT.execute(cc, dataSet);
	}

	private static void export(Connection con, File file, String tableName)
			throws DatabaseUnitException, AmbiguousTableNameException,
			IOException, DataSetException, FileNotFoundException {
		IDatabaseConnection cc = new Db2Connection(con, "website");
		cc.getConfig().setProperty(
				DatabaseConfig.PROPERTY_RESULTSET_TABLE_FACTORY,
				new ForwardOnlyResultSetTableFactory());
		QueryDataSet dataSet = new QueryDataSet(cc);
		dataSet.addTable(tableName, "select * from website.contents");
		FlatXmlDataSet.write(dataSet, new FileOutputStream(file));
		// XlsDataSet.write(dataSet, new FileOutputStream(new
		// File("data-log.xls")));
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		String driver = "org.h2.Driver";
		Class.forName(driver);
		String jdbc = "jdbc:h2:tcp://10.69.4.187:9092/test";
		Connection h2Connection = DriverManager.getConnection(
				jdbc, "sa", "");
		System.out.println(h2Connection);
		IDatabaseConnection connection = new H2Connection(h2Connection, "test");
		System.out.println(connection);

		connection.getConfig().setProperty(
				DatabaseConfig.PROPERTY_RESULTSET_TABLE_FACTORY,
				new ForwardOnlyResultSetTableFactory());
		QueryDataSet dataSet = new QueryDataSet(connection);
		dataSet.addTable("person", "select * from person");
		FlatXmlDataSet.write(dataSet, new FileOutputStream("data.xml"));
		// ITable person = connection.createTable("person");
	}
}