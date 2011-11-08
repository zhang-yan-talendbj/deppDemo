<<<<<<< HEAD
package com.caribe.stone.db.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.db2.Db2Connection;

public class DBUnitHelper {

	public static void main(String[] args) throws Exception {
		String schema = "PRECLAIM";
		String fileName = "test.xls";
		exportXls(schema, fileName);
	}

	public static void exportXls(String schema, String fileName) throws DatabaseUnitException, Exception, SQLException, IOException,
			DataSetException, FileNotFoundException {
		Db2Connection con = new Db2Connection(DBUtils.getDB2Connection(), schema);
		IDataSet fullDataSet = con.createDataSet();
		XlsDataSet.write(fullDataSet, new FileOutputStream(fileName));
	}
}
=======
package com.caribe.stone.db.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.excel.XlsDataSet;
import org.dbunit.ext.db2.Db2Connection;

public class DBUnitHelper {

	public static void main(String[] args) throws Exception {
		String schema = "PRECLAIM";
		String fileName = "test.xls";
		exportXls(schema, fileName);
	}

	public static void exportXls(String schema, String fileName) throws DatabaseUnitException, Exception, SQLException, IOException,
			DataSetException, FileNotFoundException {
		Db2Connection con = new Db2Connection(DBUtils.getDB2Connection(), schema);
		IDataSet fullDataSet = con.createDataSet();
		XlsDataSet.write(fullDataSet, new FileOutputStream(fileName));
	}
}
>>>>>>> d64a92cd44c1cd1279e7c9921940f7cb9d860b60
